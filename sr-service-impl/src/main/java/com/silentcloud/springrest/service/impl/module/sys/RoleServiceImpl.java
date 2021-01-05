package com.silentcloud.springrest.service.impl.module.sys;

import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.repository.sys.RoleRepository;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.api.module.sys.RoleService;
import com.silentcloud.springrest.service.impl.mapper.sys.RoleMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends AbstractBaseService<Long, Role, RoleDto> implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,
                           RoleMapper roleMapper) {
        super(roleRepository, roleMapper);
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

}
