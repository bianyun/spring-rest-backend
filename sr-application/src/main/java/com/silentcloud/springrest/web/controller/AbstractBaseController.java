package com.silentcloud.springrest.web.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Create;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Update;
import com.silentcloud.springrest.service.api.module.BaseService;
import com.silentcloud.springrest.util.LabelUtil;
import com.silentcloud.springrest.util.MiscUtil;
import com.silentcloud.springrest.web.IllegalActionTargetResourceException;
import com.silentcloud.springrest.web.ResourceNotFoundException;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPerm;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPermViewDetail;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPermViewList;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Persistable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

import static com.silentcloud.springrest.web.util.Consts.API_PERM_PREFIX;
import static com.silentcloud.springrest.web.util.Consts.PLACEHOLDER_LABEL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Validated
public abstract class AbstractBaseController<ID extends Serializable,
        Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>> {
    private final Class<Entity> entityClass = MiscUtil.getEntityGenericParameterClass(getClass());
    private final String label = LabelUtil.getClassLabel(MiscUtil.getDtoGenericParameterClass(getClass()));

    private final BaseService<ID, Entity, DTO> service;

    protected AbstractBaseController(BaseService<ID, Entity, DTO> service) {
        this.service = service;
    }

    @ApiOperationSupport(order = 1)
    @RequiresPermViewList
    @ApiOperation("获取所有列表")
    @GetMapping
    public List<DTO> getAll() {
        return service.findAll();
    }

    @ApiOperationSupport(order = 2)
    @RequiresPerm(name = "添加" + PLACEHOLDER_LABEL, value = API_PERM_PREFIX + "add")
    @ApiOperation("新增")
    @ResponseStatus(CREATED)
    @PostMapping
    public DTO create(@Validated(Create.class) @RequestBody DTO dto) {
        return service.create(dto);
    }

    @ApiOperationSupport(order = 3)
    @RequiresPerm(name = "更新" + PLACEHOLDER_LABEL, value = API_PERM_PREFIX + "update")
    @ApiOperation("更新")
    @PutMapping("/{id}")
    public DTO updateById(@PathVariable ID id, @Validated(Update.class) @RequestBody DTO dto) {
        if (service.existsById(id)) {
            return service.updateById(id, dto);
        } else {
            throw illegalActionTargetResourceException(id, "更新");
        }
    }

    @ApiOperationSupport(order = 4)
    @RequiresPerm(name = "删除" + PLACEHOLDER_LABEL, value = API_PERM_PREFIX + "delete")
    @ApiOperation("删除")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ID id) {
        if (service.existsById(id)) {
            service.deleteById(id);
        } else {
            throw illegalActionTargetResourceException(id, "删除");
        }
    }

    @ApiOperationSupport(order = 5)
    @RequiresPermViewDetail
    @ApiOperation("通过ID查找")
    @GetMapping("/{id}")
    public DTO readById(@PathVariable ID id) {
        DTO dto = service.findById(id);
        if (dto == null) {
            throw resourceNotFoundException(id);
        }
        return dto;
    }

    protected IllegalActionTargetResourceException illegalActionTargetResourceException(ID id, String actionLabel) {
        return new IllegalActionTargetResourceException(id, label, actionLabel);
    }

    protected ResourceNotFoundException resourceNotFoundException(ID id) {
        return new ResourceNotFoundException(id, label);
    }
}
