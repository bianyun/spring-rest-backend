package com.silentcloud.springrest.service.impl.common;

import cn.hutool.crypto.digest.BCrypt;
import com.silentcloud.springrest.service.api.common.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordEncoder implements PasswordEncoder {
    private final static int DEFAULT_BCRYPT_LOG_ROUNDS = 10;

    @Override
    public String encode(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(DEFAULT_BCRYPT_LOG_ROUNDS));
    }

    @Override
    public boolean matches(String plainPassword, String encodedPassword) {
        return BCrypt.checkpw(plainPassword, encodedPassword);
    }

}
