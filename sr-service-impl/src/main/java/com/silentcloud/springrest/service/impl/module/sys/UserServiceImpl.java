package com.silentcloud.springrest.service.impl.module.sys;

import cn.hutool.core.collection.CollUtil;
import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.repository.sys.ButtonRepository;
import com.silentcloud.springrest.repository.sys.MenuRepository;
import com.silentcloud.springrest.repository.sys.RoleRepository;
import com.silentcloud.springrest.repository.sys.UserRepository;
import com.silentcloud.springrest.service.api.common.PasswordEncoder;
import com.silentcloud.springrest.service.api.dto.sys.*;
import com.silentcloud.springrest.service.api.module.sys.UserService;
import com.silentcloud.springrest.service.impl.mapper.sys.*;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.silentcloud.springrest.jooq.gen.Tables.*;
import static com.silentcloud.springrest.service.impl.util.JooqUtil.GROUP_FIELDS_SEPARATOR;
import static org.jooq.impl.DSL.groupConcat;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractBaseService<Long, User, UserDto> implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final ButtonRepository buttonRepository;
    private final MenuRepository menuRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApiPermMapper apiPermMapper;
    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;
    private final ButtonMapper buttonMapper;


    private final Field<String> rolesName = groupConcat(SYS_ROLE.NAME).orderBy(SYS_ROLE.ID.asc())
                                                .separator(GROUP_FIELDS_SEPARATOR).as("name");
    private final Table<? extends Record> rolesGroupByUser =
            dsl.select(SYS_USER_ROLE.USER_ID, rolesName)
                    .from(SYS_USER_ROLE)
                    .join(SYS_ROLE).on(SYS_USER_ROLE.ROLE_ID.eq(SYS_ROLE.ID))
                    .groupBy(SYS_USER_ROLE.USER_ID)
                    .asTable("roles");

    private final SelectSelectStep<? extends Record> selectPartSql = dsl.select(rolesGroupByUser.field(rolesName))
            .select(SYS_USER.fields());

    private final Table<? extends Record> joinedTable = SYS_USER.leftJoin(rolesGroupByUser)
            .on(SYS_USER.ID.eq(rolesGroupByUser.field(SYS_USER_ROLE.USER_ID)));


    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "RedundantSuppression"})
    @Autowired
    public UserServiceImpl(DSLContext dsl,
                           UserRepository userRepository,
                           UserMapper userMapper,
                           RoleRepository roleRepository,
                           ButtonRepository buttonRepository,
                           MenuRepository menuRepository,
                           PasswordEncoder passwordEncoder,
                           ApiPermMapper apiPermMapper,
                           RoleMapper roleMapper,
                           MenuMapper menuMapper,
                           ButtonMapper buttonMapper) {
        super(dsl, userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.buttonRepository = buttonRepository;
        this.menuRepository = menuRepository;
        this.passwordEncoder = passwordEncoder;
        this.apiPermMapper = apiPermMapper;
        this.roleMapper = roleMapper;
        this.menuMapper = menuMapper;
        this.buttonMapper = buttonMapper;
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.entityToDto(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.entityToDto(user);
    }

    @Override
    public UserDto findByMobile(String mobile) {
        User user = userRepository.findByMobile(mobile);
        return userMapper.entityToDto(user);
    }

    @Override
    public boolean isPasswordValid(Long id, String plainPassword) {
        User user = userRepository.getOne(id);
        return passwordEncoder.matches(plainPassword, user.getPassword());
    }

    @Transactional
    @Override
    public void setPasswordById(Long id, String plainPassword) {
        User user = userRepository.getOne(id);
        String encodedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void resetPasswordById(Long id) {
        setPasswordById(id, UserDto.DEFAULT_PASSWORD);
    }


    @Override
    public List<RoleDto> getRolesByUserId(Long userId) {
        User user = userRepository.getOne(userId);
        return roleMapper.entityListToDtoList(user.getRoles());
    }

    @Override
    public Set<ApiPermDto> getApiPermsByUserId(Long userId) {
        User user = userRepository.getOne(userId);
        Set<ApiPermDto> resultSet = new HashSet<>();
        user.getRoles().forEach(role -> {
            role.getMenus().forEach(menu -> resultSet.addAll(apiPermMapper.entitySetToDtoSet(menu.getApiPerms())));
            role.getButtons().forEach(button -> resultSet.addAll(apiPermMapper.entitySetToDtoSet(button.getApiPerms())));
        });
        return resultSet;
    }

    @Override
    public Set<MenuDto> getMenuPermsByUserId(Long userId) {
        User user = userRepository.getOne(userId);
        return user.getRoles().stream().map(Role::getMenus)
                .flatMap(Set::stream).map(menuMapper::entityToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<ButtonDto> getButtonPermsByUserId(Long userId) {
        User user = userRepository.getOne(userId);
        return user.getRoles().stream().map(Role::getButtons)
                .flatMap(Set::stream).map(buttonMapper::entityToDto).collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public void saveRoleIdsByUserId(Long id, Set<Long> roleIds) {
        User user = userRepository.getOne(id);
        Set<Long> existingRoleIds = user.getRoles().stream().map(Role::getId).collect(Collectors.toSet());

        if (!CollUtil.disjunction(roleIds, existingRoleIds).isEmpty()) {
            List<Role> roles = roleIds.stream().map(roleRepository::getOne).collect(Collectors.toList());
            user.setRoles(roles);
            userRepository.save(user);
        }

    }

    @Override
    protected SelectSelectStep<? extends Record> buildSelectPartSql() {
        return selectPartSql;
    }

    @Override
    protected Table<? extends Record> buildJoinedTable() {
        return joinedTable;
    }

}
