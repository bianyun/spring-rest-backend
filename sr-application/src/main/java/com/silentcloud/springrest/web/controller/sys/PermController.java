package com.silentcloud.springrest.web.controller.sys;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import com.silentcloud.springrest.service.api.module.sys.ApiPermService;
import com.silentcloud.springrest.service.api.module.sys.ButtonService;
import com.silentcloud.springrest.service.api.module.sys.MenuService;
import com.silentcloud.springrest.web.vo.ApiPermMetaData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "权限管理")
@ApiSupport(order = 3)
@RequestMapping("/sys/perms")
@RestController
public class PermController {
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

    @ApiOperation("获取接口权限元数据")
    @GetMapping("/api-meta")
    public ApiPermMetaData getApiPermMetaData() {
        // TODO 获取 ApiPerm 元数据
        return null;
    }

    @ApiOperation("同步接口权限数据")
    @PostMapping("/sync-api")
    public void syncApiPermTreeData(@RequestBody List<ApiPermDto> apiPermTreeData) {
        // TODO 同步 ApiPerm Tree Data
    }

    @ApiOperation("同步菜单权限数据")
    @PostMapping("/sync-menu")
    public void syncMenuTreeData(@RequestBody List<MenuDto> menuTreeData) {
        // TODO 同步 Menu Tree Data
    }
}
