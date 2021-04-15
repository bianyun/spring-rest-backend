package com.silentcloud.springrest.service.impl.module.sys;

import cn.hutool.core.collection.CollUtil;
import com.silentcloud.springrest.model.entity.sys.ApiPerm;
import com.silentcloud.springrest.model.entity.sys.Menu;
import com.silentcloud.springrest.repository.sys.ApiPermRepository;
import com.silentcloud.springrest.repository.sys.MenuRepository;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import com.silentcloud.springrest.service.api.module.sys.MenuService;
import com.silentcloud.springrest.service.impl.mapper.sys.ApiPermMapper;
import com.silentcloud.springrest.service.impl.mapper.sys.ButtonMapper;
import com.silentcloud.springrest.service.impl.mapper.sys.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单权限服务实现
 *
 * @author bianyun
 */
@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final ButtonMapper buttonMapper;
    private final ApiPermMapper apiPermMapper;
    private final ApiPermRepository apiPermRepository;

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "RedundantSuppression"})
    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository,
                           MenuMapper menuMapper,
                           ButtonMapper buttonMapper,
                           ApiPermMapper apiPermMapper,
                           ApiPermRepository apiPermRepository) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.buttonMapper = buttonMapper;
        this.apiPermMapper = apiPermMapper;
        this.apiPermRepository = apiPermRepository;
    }

    @Override
    public List<MenuDto> findAll() {
        return menuMapper.entityListToDtoList(menuRepository.findAll());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(MenuDto dto) {
        Menu savedEntity = menuRepository.save(menuMapper.dtoToEntity(dto));
        menuMapper.entityToDto(savedEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(Long id, MenuDto dto) {
        Menu entity = menuRepository.getOne(id);
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        menuRepository.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public List<ButtonDto> getButtonsByMenuId(Long menuId) {
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

    @Override
    public Map<String, Set<String>> getMenuToApiPermValuesMap() {
        List<Menu> allMenus = menuRepository.findAll();
        Map<String, Set<String>> resultMap = new HashMap<>(16);
        allMenus.forEach(menu -> {
            String menuPermValue = menu.getValue();
            Set<String> apiPermValueSet = menu.getApiPerms().stream()
                    .map(ApiPerm::getValue).collect(Collectors.toSet());
            resultMap.put(menuPermValue, apiPermValueSet);
        });

        return resultMap;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void linkApiPermsToMenu(String menuPermValue, Set<String> apiPermValueSet) {
        Menu menu = menuRepository.findByValue(menuPermValue);
        Set<String> apiPermValueSetInDb = menu.getApiPerms().stream()
                .map(ApiPerm::getValue).collect(Collectors.toSet());

        if (!CollUtil.disjunction(apiPermValueSet, apiPermValueSetInDb).isEmpty()) {
            Set<ApiPerm> apiPerms = apiPermValueSet.stream()
                    .map(apiPermRepository::findByValue).collect(Collectors.toSet());
            menu.setApiPerms(apiPerms);
            menuRepository.save(menu);
        }
    }
}
