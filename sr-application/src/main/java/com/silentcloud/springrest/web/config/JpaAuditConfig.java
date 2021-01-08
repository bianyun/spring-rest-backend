package com.silentcloud.springrest.web.config;

import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.repository.sys.UserRepository;
import com.silentcloud.springrest.service.api.dto.sys.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {

    private final UserRepository userRepository;

    public JpaAuditConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public AuditorAware<User> auditorProvider() {
        return () -> {
            Subject subject = SecurityUtils.getSubject();
            UserDto currentUser = (UserDto) subject.getPrincipal();
            return userRepository.findById(currentUser.getId());
        };
    }

}
