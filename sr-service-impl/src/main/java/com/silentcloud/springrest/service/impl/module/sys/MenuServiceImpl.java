package com.silentcloud.springrest.service.impl.module.sys;

import com.silentcloud.springrest.model.entity.sys.Menu;
import com.silentcloud.springrest.repository.sys.MenuRepository;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import com.silentcloud.springrest.service.api.module.sys.MenuService;
import com.silentcloud.springrest.service.impl.mapper.sys.ApiPermMapper;
import com.silentcloud.springrest.service.impl.mapper.sys.ButtonMapper;
import com.silentcloud.springrest.service.impl.mapper.sys.MenuMapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final ButtonMapper buttonMapper;
    private final ApiPermMapper apiPermMapper;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository,
                           MenuMapper menuMapper,
                           ButtonMapper buttonMapper,
                           ApiPermMapper apiPermMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.buttonMapper = buttonMapper;
        this.apiPermMapper = apiPermMapper;
    }

    @Override
    public List<MenuDto> findAll() {
        return menuMapper.entityListToDtoList(menuRepository.findAll());
    }

    @Transactional
    @Override
    public MenuDto create(@NonNull MenuDto dto) {
        Menu savedEntity = menuRepository.save(menuMapper.dtoToEntity(dto));
        return menuMapper.entityToDto(savedEntity);
    }

    @Transactional
    @Override
    public void updateById(Long id, MenuDto dto) {
        Menu entity = menuRepository.getOne(id);
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        menuRepository.save(entity);
    }

    @Transactional
    @Override
    public void deleteById(@NonNull Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public MenuDto findById(@NonNull Long id) {
        return menuMapper.entityToDto(menuRepository.getOne(id));
    }

    @Override
    public List<ButtonDto> getButtonsByMenuId(@NonNull Long menuId) {
        Menu menu = menuRepository.getOne(menuId);
        return buttonMapper.entityListToDtoList(menu.getButtons());
    }

    @Override
    public Set<ApiPermDto> getApiPermsByMenuId(Long menuId) {
        Menu menu = menuRepository.getOne(menuId);
        return apiPermMapper.entitySetToDtoSet(menu.getApiPerms());
    }

    @Override
    public List<String> getAllMenuPermValuesInDb() {
        List<Menu> menus = menuRepository.findAllByOrderByValue();
        return menus.stream().map(Menu::getValue).collect(Collectors.toList());
    }

    @Override
    public MenuDto findByValue(String value) {
        return menuMapper.entityToDto(menuRepository.findByValue(value));
    }
}
