package com.silentcloud.springrest.service.impl.module.sys;

import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.repository.sys.UserRepository;
import com.silentcloud.springrest.service.api.dto.sys.UserDto;
import com.silentcloud.springrest.service.api.module.sys.UserService;
import com.silentcloud.springrest.service.impl.mapper.sys.UserMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractBaseService<Long, User, UserDto> implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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

    @Transactional
    @Override
    public void setPasswordById(Long id, String plainPassword) {

    }

    @Transactional
    @Override
    public void resetPasswordById(Long id) {
        setPasswordById(id, UserDto.DEFAULT_PASSWORD);
    }

}
