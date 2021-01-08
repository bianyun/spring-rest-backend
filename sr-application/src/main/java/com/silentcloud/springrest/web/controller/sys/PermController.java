package com.silentcloud.springrest.web.controller.sys;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Create;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Update;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import com.silentcloud.springrest.service.api.module.sys.ApiPermService;
import com.silentcloud.springrest.service.api.module.sys.ButtonService;
import com.silentcloud.springrest.service.api.module.sys.MenuService;
import com.silentcloud.springrest.util.LabelUtil;
import com.silentcloud.springrest.util.MiscUtil;
import com.silentcloud.springrest.web.IllegalActionTargetResourceException;
import com.silentcloud.springrest.web.controller.AbstractBaseController;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPerm;
import com.silentcloud.springrest.web.util.ApiGroup;
import com.silentcloud.springrest.web.vo.ApiPermMetaData;
import com.silentcloud.springrest.web.vo.MenuPermMetaData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static com.silentcloud.springrest.web.util.Consts.*;

@Api(tags = "权限管理")
@ApiSupport(order = 3)
@RequestMapping("/sys/perms")
@RestController
public class PermController {
    public static final Map<String, String> API_PERM_VALUE_NAME_MAP = new HashMap<>();
    private final List<ApiPermDto> API_PERM_LIST = buildApiPermList();

    private final ApiPermService apiPermService;
    private final ButtonService buttonService;
    private final MenuService menuService;

    public PermController(ApiPermService apiPermService,
                          ButtonService buttonService,
                          MenuService menuService) {
        this.apiPermService = apiPermService;
        this.buttonService = buttonService;
        this.menuService = menuService;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 1)
    @RequiresPerm(name = "获取接口权限元数据", value = API_PERM_PREFIX + "fetch-api-meta")
    @ApiOperation("获取接口权限元数据")
    @GetMapping("/meta/api")
    public ApiPermMetaData getApiPermMetaData() {
        ApiPermMetaData result = new ApiPermMetaData();
        result.setApiPermList(API_PERM_LIST);
        result.setUnsyncedApiPermValues(getUnsyncedApiPermValues());
        return result;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 1)
    @RequiresPerm(name = "获取菜单权限元数据", value = API_PERM_PREFIX + "fetch-menu-meta")
    @ApiOperation("获取菜单权限元数据")
    @PostMapping("/meta/menu")
    public MenuPermMetaData fetchMenuPermMetaData(@RequestBody List<MenuDto> menuPerms) {
        MenuPermMetaData metaData = new MenuPermMetaData();
        metaData.setUnsyncedMenuPermValues(getUnsyncedMenuPermValues(menuPerms));
        metaData.setMenuToButtonPermsMap(buttonService.getMenuToButtonPermsMap());
        return metaData;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 2)
    @RequiresPerm(name = "同步接口权限数据", value = API_PERM_PREFIX + "sync-api")
    @ApiOperation("同步接口权限数据")
    @PostMapping("/sync-api-perms-to-db")
    public void syncApiPermsToDb(@RequestBody List<ApiPermDto> apiPermTreeData) {
        for (ApiPermDto packageLevelPerm : apiPermTreeData) {
            for (ApiPermDto classLevelPerm : packageLevelPerm.getChildren()) {
                classLevelPerm.getChildren().forEach(methodLevelPerm -> methodLevelPerm.setParent(classLevelPerm));

                syncApiPermToDb(classLevelPerm);
                for (ApiPermDto methodLevelPerm : classLevelPerm.getChildren()) {
                    syncApiPermToDb(methodLevelPerm);
                }
            }
        }
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 3)
    @RequiresPerm(name = "同步菜单权限数据", value = API_PERM_PREFIX + "sync-menu")
    @ApiOperation("同步菜单权限数据")
    @PostMapping("/sync-menu-perms-to-db")
    public void syncMenuPermsToDb(@RequestBody List<MenuDto> menuTreeData) {
        for (MenuDto menu : menuTreeData) {
            menu.getChildren().forEach(childMenu -> childMenu.setParent(menu));
            syncMenuPermToDb(menu);
            for (MenuDto childMenu : menu.getChildren()) {
                syncMenuPermToDb(childMenu);
            }
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 4)
    @RequiresPerm(name = "添加按钮权限", value = API_PERM_PREFIX + "button:add")
    @ApiOperation("添加按钮权限")
    @PostMapping("/buttons")
    public void addButtonPerm(@Validated(Create.class) @RequestBody ButtonDto buttonDto) {
        buttonService.create(buttonDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 5)
    @RequiresPerm(name = "更新按钮权限", value = API_PERM_PREFIX + "button:update")
    @ApiOperation("更新按钮权限")
    @PutMapping("/buttons/{id}")
    public void updateButtonPerm(@PathVariable Long id, @Validated(Update.class) @RequestBody ButtonDto buttonDto) {
        if (buttonService.existsById(id)) {
            buttonService.updateById(id, buttonDto);
        } else {
            throw new IllegalActionTargetResourceException(id, "按钮权限", "更新");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 6)
    @RequiresPerm(name = "删除按钮权限", value = API_PERM_PREFIX + "button:delete")
    @ApiOperation("删除按钮权限")
    @DeleteMapping("/buttons/{id}")
    public void deleteButtonPerm(@PathVariable Long id) {
        if (buttonService.existsById(id)) {
            buttonService.deleteById(id);
        } else {
            throw new IllegalActionTargetResourceException(id, "按钮权限", "删除");
        }
    }

    private static List<ApiPermDto> buildApiPermList() {
        return getModulePackages().stream()
                .map(PermController::generatePackageLevelApiPerm)
                .collect(Collectors.toList());
    }

    private static List<Package> getModulePackages() {
        String baseControllerPackage = AbstractBaseController.class.getPackage().getName();

        return ClassUtil.scanPackage(baseControllerPackage,
                clazz -> ClassUtil.isNormalClass(clazz) && !ReflectUtil.getPublicMethods(clazz,
                        method -> method.isAnnotationPresent(RequiresPerm.class)).isEmpty())
                .stream().map(Class::getPackage).distinct()
                .sorted(Comparator.comparing(Package::getName))
                .collect(Collectors.toList());
    }

    private static ApiPermDto generatePackageLevelApiPerm(Package pkg) {
        ApiPermDto packageLevelPerm = new ApiPermDto();
        packageLevelPerm.setName(AnnotationUtil.getAnnotationValue(pkg, ApiGroup.class));

        String packageDomain = StrUtil.subAfter(pkg.getName(), "controller.", false);
        packageLevelPerm.setValue((API_PERM_PREFIX + "manage").replace(PLACEHOLDER_DOMAIN, packageDomain));

        List<ApiPermDto> classLevelPerms = getControllerClassesUnderPackage(pkg).stream()
                .map(PermController::generateClassLevelApiPerm).collect(Collectors.toList());
        packageLevelPerm.setChildren(classLevelPerms);
        return packageLevelPerm;
    }

    private static List<Class<?>> getControllerClassesUnderPackage(Package pkg) {
        Comparator<Class<?>> comparator = (class1, class2) -> {
            Integer order1 = AnnotationUtil.getAnnotationValue(class1, ApiSupport.class, "order");
            Integer order2 = AnnotationUtil.getAnnotationValue(class2, ApiSupport.class, "order");

            if (order1 == null && order2 == null) {
                return class1.getSimpleName().compareTo(class2.getSimpleName());
            } else if (order1 != null && order2 != null) {
                return order1 - order2;
            } else if (order1 != null) {
                return -1;
            } else {
                return 1;
            }
        };

        return ClassUtil.scanPackage(pkg.getName(),
                clazz -> ClassUtil.isNormalClass(clazz) && !ReflectUtil.getPublicMethods(clazz,
                        method -> AnnotationUtil.hasAnnotation(method, RequiresPerm.class)).isEmpty())
                .stream().sorted(comparator).collect(Collectors.toList());
    }

    private static ApiPermDto generateClassLevelApiPerm(Class<?> controllerClass) {
        ApiPermDto classLevelPerm = new ApiPermDto();

        String permName;
        String[] tags = AnnotationUtil.getAnnotationValue(controllerClass, Api.class, "tags");
        if (tags == null || tags.length == 0 || StrUtil.isBlank(tags[0])) {
            permName = controllerClass.getSimpleName() + " 管理";
        } else {
            permName = tags[0];
        }
        classLevelPerm.setName(permName);

        String label = LabelUtil.getClassLabel(MiscUtil.getDtoGenericParameterClass(controllerClass));
        String domain = MiscUtil.parseDomainOfControllerClass(controllerClass);
        String permValue = (API_PERM_PREFIX + "manage").replace(PLACEHOLDER_DOMAIN, domain);
        classLevelPerm.setValue(permValue);

        List<Method> methods = getMethodsUnderClass(controllerClass);
        List<ApiPermDto> methodLevelPerms = methods.stream()
                .map(method -> generateMethodLevelApiPerm(permName, method, label, domain))
                .distinct().collect(Collectors.toList());
        classLevelPerm.setChildren(methodLevelPerms);
        return classLevelPerm;
    }

    private static List<Method> getMethodsUnderClass(Class<?> controllerClass) {
        Comparator<Method> comparator = (method1, method2) -> {
            Integer order1 = AnnotationUtil.getAnnotationValue(method1, ApiOperationSupport.class, "order");
            Integer order2 = AnnotationUtil.getAnnotationValue(method2, ApiOperationSupport.class, "order");

            String permValue1 = AnnotationUtil.getAnnotationValue(method1, RequiresPerm.class);
            String permValue2 = AnnotationUtil.getAnnotationValue(method2, RequiresPerm.class);

            if (order1 == null && order2 == null) {
                return permValue1.compareTo(permValue2);
            } else if (order1 != null && order2 != null) {
                return order1 - order2;
            } else if (order1 != null) {
                return -1;
            } else {
                return 1;
            }
        };

        return ReflectUtil.getPublicMethods(controllerClass,
                method -> AnnotationUtil.hasAnnotation(method, RequiresPerm.class))
                .stream().sorted(comparator).collect(Collectors.toList());
    }

    private static ApiPermDto generateMethodLevelApiPerm(String classPermName, Method method, String label, String domain) {
        ApiPermDto methodLevelPerm = new ApiPermDto();

        String permName = AnnotationUtil.getAnnotationValue(method, RequiresPerm.class, "name");
        String permValue = AnnotationUtil.getAnnotationValue(method, RequiresPerm.class, "value");

        permName = permName.replace(PLACEHOLDER_LABEL, label);
        permValue = permValue.replace(PLACEHOLDER_DOMAIN, domain).toLowerCase();

        API_PERM_VALUE_NAME_MAP.put(permValue, classPermName + " => " + permName);

        methodLevelPerm.setName(permName);
        methodLevelPerm.setValue(permValue);
        methodLevelPerm.setChildren(null);
        return methodLevelPerm;
    }

    private List<String> getUnsyncedApiPermValues() {
        List<String> resultSet = new ArrayList<>();
        for (ApiPermDto packageLevelPerm : API_PERM_LIST) {
            for (ApiPermDto classLevelPerm : packageLevelPerm.getChildren()) {
                if (isApiPermNotSyncedWithDbRecord(classLevelPerm)) {
                    resultSet.add(classLevelPerm.getValue());
                }

                for (ApiPermDto methodLevelPerm : classLevelPerm.getChildren()) {
                    if (isApiPermNotSyncedWithDbRecord(methodLevelPerm)) {
                        resultSet.add(methodLevelPerm.getValue());
                    }
                }
            }
        }

        return resultSet;
    }

    private List<String> getUnsyncedMenuPermValues(List<MenuDto> menuPerms) {
        List<String> resultSet = new ArrayList<>();
        for (MenuDto menuPerm : menuPerms) {
            if (isMenuPermNotSyncedWithDbRecord(menuPerm)) {
                resultSet.add(menuPerm.getValue());
            }

            for (MenuDto childMenuPerm : menuPerm.getChildren()) {
                if (isMenuPermNotSyncedWithDbRecord(childMenuPerm)) {
                    resultSet.add(childMenuPerm.getValue());
                }
            }
        }

        return resultSet;
    }


    private boolean isApiPermNotSyncedWithDbRecord(ApiPermDto perm) {
        ApiPermDto permInDb = apiPermService.findByValue(perm.getValue());
        perm.setId(permInDb != null ? permInDb.getId() : null);
        return permInDb == null || !permInDb.getName().equals(perm.getName());
    }

    private void syncApiPermToDb(ApiPermDto perm) {
        if (isApiPermNotSyncedWithDbRecord(perm)) {
            doSyncApiPermToDb(perm);
        }
    }

    private void doSyncApiPermToDb(ApiPermDto perm) {
        ApiPermDto permInDb = apiPermService.findByValue(perm.getValue());
        if (permInDb == null) {
            apiPermService.create(perm);
        } else {
            apiPermService.updateById(permInDb.getId(), perm);
        }
    }

    private boolean isMenuPermNotSyncedWithDbRecord(MenuDto perm) {
        MenuDto permInDb = menuService.findByValue(perm.getValue());
        perm.setId(permInDb != null ? permInDb.getId() : null);
        return permInDb == null || !permInDb.getName().equals(perm.getName());
    }

    private void syncMenuPermToDb(MenuDto perm) {
        if (isMenuPermNotSyncedWithDbRecord(perm)) {
            doSyncMenuPermToDb(perm);
        }
    }

    private void doSyncMenuPermToDb(MenuDto perm) {
        MenuDto permInDb = menuService.findByValue(perm.getValue());
        if (permInDb == null) {
            menuService.create(perm);
        } else {
            menuService.updateById(permInDb.getId(), perm);
        }
    }
}
