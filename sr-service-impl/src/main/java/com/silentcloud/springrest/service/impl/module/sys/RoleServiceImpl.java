package com.silentcloud.springrest.service.impl.module.sys;

import cn.hutool.core.collection.CollUtil;
import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.model.entity.sys.Menu;
import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.repository.sys.ButtonRepository;
import com.silentcloud.springrest.repository.sys.MenuRepository;
import com.silentcloud.springrest.repository.sys.RoleRepository;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.api.module.sys.RoleService;
import com.silentcloud.springrest.service.impl.mapper.sys.ButtonMapper;
import com.silentcloud.springrest.service.impl.mapper.sys.MenuMapper;
import com.silentcloud.springrest.service.impl.mapper.sys.RoleMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static com.silentcloud.springrest.jooq.gen.Tables.SYS_ROLE;

/**
 * 角色服务实现
 *
 * @author bianyun
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends AbstractBaseService<Long, Role, RoleDto> implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;
    private final ButtonMapper buttonMapper;
    private final MenuRepository menuRepository;
    private final ButtonRepository buttonRepository;

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "RedundantSuppression"})
    @Autowired
    public RoleServiceImpl(DSLContext dsl,
                           RoleRepository roleRepository,
                           RoleMapper roleMapper,
                           MenuMapper menuMapper,
                           ButtonMapper buttonMapper,
                           MenuRepository menuRepository,
                           ButtonRepository buttonRepository) {
        super(dsl, roleRepository, roleMapper);
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.menuMapper = menuMapper;
        this.buttonMapper = buttonMapper;
        this.menuRepository = menuRepository;
        this.buttonRepository = buttonRepository;
    }

    @Override
    protected Table<? extends Record> buildJoinedTable() {
        return SYS_ROLE;
    }

    @Override
    public Set<MenuDto> getMenusByRoleId(Long roleId) {
        Role role = roleRepository.getOne(roleId);
        return menuMapper.entitySetToDtoSet(role.getMenus());
    }

    @Override
    public Set<ButtonDto> getButtonsByRoleId(Long roleId) {
        Role role = roleRepository.getOne(roleId);
        return buttonMapper.entitySetToDtoSet(role.getButtons());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void assignPerms(Long roleId, Set<String> menuPermValueSet, Set<String> btnPermValueSet) {
        Role role = roleRepository.getOne(roleId);

        Set<String> menuPermValueSetInDb = role.getMenus().stream().map(Menu::getValue).collect(Collectors.toSet());
        Set<String> btnPermValueSetInDb = role.getButtons().stream().map(Button::getValue).collect(Collectors.toSet());

        if (!CollUtil.disjunction(menuPermValueSet, menuPermValueSetInDb).isEmpty() ||
                !CollUtil.disjunction(btnPermValueSet, btnPermValueSetInDb).isEmpty()) {
            if (!CollUtil.disjunction(menuPermValueSet, menuPermValueSetInDb).isEmpty()) {
                Set<Menu> menuPerms = menuPermValueSet.stream().map(menuRepository::findByValue).collect(Collectors.toSet());
                role.setMenus(menuPerms);
            }

            if (!CollUtil.disjunction(btnPermValueSet, btnPermValueSetInDb).isEmpty()) {
                Set<Button> buttonPerms = btnPermValueSet.stream().map(buttonRepository::findByValue).collect(Collectors.toSet());
                role.setButtons(buttonPerms);
            }

            roleRepository.save(role);
        }

    }
}
