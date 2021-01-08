package com.silentcloud.springrest.service.impl.mapper.sys;

import cn.hutool.core.lang.Assert;
import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.repository.sys.MenuRepository;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class ButtonMapper implements BaseMapper<Long, Button, ButtonDto> {

    @Autowired
    private MenuRepository menuRepository;

    @Mapping(target = "permType", ignore = true)
    @Mapping(target = "parentMenuName", source = "parentMenu.name")
    @Mapping(target = "parentMenuValue", source = "parentMenu.value")
    @Override
    public abstract ButtonDto entityToDto(Button entity);

    @Mapping(target = "parentMenu", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "apiPerms", ignore = true)
    @Override
    public abstract Button dtoToEntity(ButtonDto dto);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "parentMenu", ignore = true)
    @Mapping(target = "apiPerms", ignore = true)
    @Override
    public abstract void updateEntityFromDto(ButtonDto dto, @MappingTarget Button entity);

    @AfterMapping
    public void establishRelations(ButtonDto dto, @MappingTarget Button entity) {
        String parentMenuValue = dto.getParentMenuValue();
        Assert.notBlank(parentMenuValue);
        entity.setParentMenu(menuRepository.findByValue(parentMenuValue));
    }
}
