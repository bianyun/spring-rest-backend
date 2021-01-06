package com.silentcloud.springrest.web.controller;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Create;
import com.silentcloud.springrest.service.api.dto.ValidationGroups.Update;
import com.silentcloud.springrest.service.api.module.BaseService;
import com.silentcloud.springrest.util.LabelUtil;
import com.silentcloud.springrest.util.MiscUtil;
import com.silentcloud.springrest.web.IllegalActionTargetResourceException;
import com.silentcloud.springrest.web.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Persistable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

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

    @ApiOperation("获取所有列表")
    @GetMapping
    public List<DTO> getAll() {
        return service.findAll();
    }

    @ApiOperation("新增")
    @ResponseStatus(CREATED)
    @PostMapping
    public DTO create(@Validated(Create.class) @RequestBody DTO dto) {
        return service.create(dto);
    }

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

    @ApiOperation("修改")
    @PutMapping("/{id}")
    public DTO updateById(@PathVariable ID id, @Validated(Update.class) @RequestBody DTO dto) {
        if (service.existsById(id)) {
            return service.updateById(id, dto);
        } else {
            throw illegalActionTargetResourceException(id, "更新");
        }
    }

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
