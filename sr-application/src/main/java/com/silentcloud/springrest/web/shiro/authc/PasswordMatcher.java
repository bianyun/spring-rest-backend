package com.silentcloud.springrest.web.shiro.authc;

import com.silentcloud.springrest.service.api.common.PasswordEncoder;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.stereotype.Component;

@Component
public class PasswordMatcher implements CredentialsMatcher {
    private final PasswordEncoder passwordEncoder;

    public PasswordMatcher(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String plainPassword = new String((char[]) token.getCredentials());
        String encodedPassword = (String) info.getCredentials();
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }

}
