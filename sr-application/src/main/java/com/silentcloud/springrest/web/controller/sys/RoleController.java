package com.silentcloud.springrest.web.controller.sys;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.api.module.sys.RoleService;
import com.silentcloud.springrest.service.api.query.FlatQueryService;
import com.silentcloud.springrest.service.api.query.JpaQueryService;
import com.silentcloud.springrest.web.controller.AbstractBaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "角色管理")
@ApiSupport(order = 2)
@RequestMapping("/sys/roles")
@RestController
public class RoleController extends AbstractBaseController<Long, Role, RoleDto> {
    private final RoleService roleService;

    @Autowired
    public RoleController(JpaQueryService jpaQueryService,
                          FlatQueryService flatQueryService,
                          RoleService roleService) {
        super(jpaQueryService, flatQueryService, roleService);
        this.roleService = roleService;
    }
}
