package com.silentcloud.springrest.service.impl.module.sys;

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
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


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

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           RoleRepository roleRepository,
                           ButtonRepository buttonRepository,
                           MenuRepository menuRepository,
                           PasswordEncoder passwordEncoder,
                           ApiPermMapper apiPermMapper,
                           RoleMapper roleMapper,
                           MenuMapper menuMapper,
                           ButtonMapper buttonMapper) {
        super(userRepository, userMapper);
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
    public UserDto findByUsername(@NonNull String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.entityToDto(user);
    }

    @Override
    public UserDto findByEmail(@NonNull String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.entityToDto(user);
    }

    @Override
    public UserDto findByMobile(@NonNull String mobile) {
        User user = userRepository.findByMobile(mobile);
        return userMapper.entityToDto(user);
    }

    @Override
    public boolean isPasswordValid(@NonNull Long id, @NonNull String plainPassword) {
        User user = userRepository.getOne(id);
        return passwordEncoder.matches(plainPassword, user.getPassword());
    }

    @Transactional
    @Override
    public void setPasswordById(@NonNull Long id, @NonNull String plainPassword) {
        User user = userRepository.getOne(id);
        String encodedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void resetPasswordById(@NonNull Long id) {
        setPasswordById(id, UserDto.DEFAULT_PASSWORD);
    }


    @Override
    public Set<RoleDto> getRolesByUserId(Long userId) {
        User user = userRepository.getOne(userId);
        return roleMapper.entitySetToDtoSet(user.getRoles());
    }

    @Override
    public Set<ApiPermDto> getApiPermsByUserId(@NonNull Long userId) {
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

}
