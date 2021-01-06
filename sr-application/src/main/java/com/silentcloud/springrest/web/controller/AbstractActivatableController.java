package com.silentcloud.springrest.web.controller;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.module.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Persistable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

import static org.springframework.http.HttpStatus.NO_CONTENT;

public abstract class AbstractActivatableController<ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
        extends AbstractBaseController<ID, Entity, DTO> {
    private final BaseService<ID, Entity, DTO> service;

    protected AbstractActivatableController(BaseService<ID, Entity, DTO> service) {
        super(service);
        this.service = service;
    }

    @ApiOperation("启用")
    @ResponseStatus(NO_CONTENT)
    @PostMapping("/{id}/activate")
    public void activateById(@PathVariable ID id) {
        if (service.existsById(id)) {
            service.activateById(id);
        } else {
            throw illegalActionTargetResourceException(id, "启用");
        }
    }

    @ApiOperation("停用")
    @ResponseStatus(NO_CONTENT)
    @PostMapping("/{id}/deactivate")
    public void deactivateById(@PathVariable ID id) {
        if (service.existsById(id)) {
            service.deactivateById(id);
        } else {
            throw illegalActionTargetResourceException(id, "停用");
        }
    }

}
