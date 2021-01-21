package com.silentcloud.springrest.web.controller.sys;

import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.EnumUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.model.enums.Country;
import com.silentcloud.springrest.model.enums.base.EnumConst;
import com.silentcloud.springrest.service.impl.util.JooqUtil;
import com.silentcloud.springrest.util.MiscUtil;
import com.silentcloud.springrest.web.vo.misc.EnumLabelNameMap;
import com.silentcloud.springrest.web.vo.misc.SelectOption;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.silentcloud.springrest.web.util.Consts.APP_ROOT_PACKAGE_NAME;

@Api(tags = "杂项接口")
@ApiSupport(order = 4)
@RequestMapping("/sys/misc")
@RestController
public class MiscController {
    private static final EnumLabelNameMap ENUM_LABEL_NAME_MAP = buildEnumLabelNameMap();
    private static final List<SelectOption> COUNTRY_CODE_OPTIONS = buildCountryCodeOptions();

    @ApiOperation("获取枚举类的字段名称映射表")
    @GetMapping("/enum-label-name-map")
    public EnumLabelNameMap getEnumLabelNameMap() {
        return ENUM_LABEL_NAME_MAP;
    }

    @ApiOperation("获取国家代码选项数据")
    @GetMapping("/country-code-options")
    public List<SelectOption> getCountryCodeOptions() {
        return COUNTRY_CODE_OPTIONS;
    }

    @SuppressWarnings("unchecked")
    private static EnumLabelNameMap buildEnumLabelNameMap() {
        EnumLabelNameMap resultMap = new EnumLabelNameMap();

        Filter<Class<?>> filter = clazz -> clazz.isEnum() && (EnumConst.class.isAssignableFrom(clazz)) &&
                !JooqUtil.ENUM_DICT_MAP_BLACK_LIST.contains(clazz);
        Set<Class<?>> enumClasses = ClassUtil.scanPackage(APP_ROOT_PACKAGE_NAME, filter);

        for (Class<?> clazz : enumClasses) {
            Class<? extends Enum<?>> enumClazz = (Class<? extends Enum<?>>) clazz;
            Map<String, Object> result = MiscUtil.getNameFieldMap(enumClazz, "name");
            assert result != null;

            List<EnumLabelNameMap.Entry> labelNameMapEntries = result.entrySet()
                    .stream().map(entry -> EnumLabelNameMap.Entry.builder()
                            .label(entry.getKey())
                            .name((String) entry.getValue()).build())
                    .collect(Collectors.toList());
            resultMap.put(clazz.getSimpleName(), labelNameMapEntries);
        }

        return resultMap;
    }

    private static List<SelectOption> buildCountryCodeOptions() {
        List<SelectOption> resultList = new ArrayList<>();

        EnumUtil.getEnumMap(Country.class).forEach((key, value) ->
                resultList.add(SelectOption.builder()
                        .label(value.getChsName()).value(value.getAlpha2()).build()));

        return resultList;
    }

}
