package com.silentcloud.springrest.web.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.module.BaseService;
import com.silentcloud.springrest.service.api.query.FlatQueryService;
import com.silentcloud.springrest.service.api.query.JpaQueryService;
import com.silentcloud.springrest.web.IllegalWebOperationException;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPerm;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Persistable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

import static com.silentcloud.springrest.web.util.Consts.*;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * 支持启用停用操作的公共 Controller（继承自 抽象公共Controller）
 *
 * @param <ID>     实体对象ID类型
 * @param <Entity> 实体对象类型
 * @param <DTO>    DTO类型
 * @author bianyun
 */
public abstract class AbstractActivatableController<ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
        extends AbstractBaseController<ID, Entity, DTO> {
    private final BaseService<ID, Entity, DTO> service;

    protected AbstractActivatableController(JpaQueryService jpaQueryService,
                                            FlatQueryService flatQueryService,
                                            BaseService<ID, Entity, DTO> service) {
        super(jpaQueryService, flatQueryService, service);
        this.service = service;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET - 2)
    @RequiresPerm(name = "启用停用" + PLACEHOLDER_LABEL, value = API_PERM_PREFIX + "active-on-off")
    @ApiOperation("启用")
    @ResponseStatus(NO_CONTENT)
    @PostMapping("/{id}/activate")
    public void activateById(@PathVariable ID id) {
        if (service.existsById(id)) {
            service.activateById(id);
        } else {
            throw actionTargetResourceNotFoundException(id, "启用");
        }
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET - 1)
    @RequiresPerm(name = "启用停用" + PLACEHOLDER_LABEL, value = API_PERM_PREFIX + "active-on-off")
    @ApiOperation("停用")
    @ResponseStatus(NO_CONTENT)
    @PostMapping("/{id}/deactivate")
    public void deactivateById(@PathVariable ID id) {
        if (service.existsById(id)) {
            if (User.class.equals(entityClass)) {
                if (getCurrentUserId().equals(id)) {
                    throw new IllegalWebOperationException("不能停用当前用户自己的账号");
                }
            }

            service.deactivateById(id);
        } else {
            throw actionTargetResourceNotFoundException(id, "停用");
        }
    }

}
