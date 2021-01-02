package com.silentcloud.springrest.repository.sys;


import com.silentcloud.springrest.repository.BaseRepository;
import com.silentcloud.springrest.model.entity.sys.User;

public interface UserRepository extends BaseRepository<Long, User> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByMobile(String mobile);

}
