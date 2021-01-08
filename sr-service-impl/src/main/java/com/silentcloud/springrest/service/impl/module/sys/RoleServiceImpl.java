package com.silentcloud.springrest.service.impl.module.sys;

import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.repository.sys.RoleRepository;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.api.module.sys.RoleService;
import com.silentcloud.springrest.service.impl.mapper.sys.RoleMapper;
import com.silentcloud.springrest.service.impl.module.AbstractBaseService;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.silentcloud.spring.rest.jooq.gen.Tables.SYS_ROLE;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends AbstractBaseService<Long, Role, RoleDto> implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(DSLContext dsl,
                           RoleRepository roleRepository,
                           RoleMapper roleMapper) {
        super(dsl, roleRepository, roleMapper);
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    protected Table<? extends Record> buildJoinedTable() {
        return SYS_ROLE;
    }

}
