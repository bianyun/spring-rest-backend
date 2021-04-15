package com.silentcloud.springrest.repository.sys;


import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.repository.BaseRepository;

/**
 * 用户 Repository
 *
 * @author bianyun
 */
public interface UserRepository extends BaseRepository<Long, User> {

    /**
     * 根据 用户名 查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findByUsername(String username);

    /**
     * 根据 Email 查找用户
     *
     * @param email Email
     * @return 用户
     */
    User findByEmail(String email);

    /**
     * 根据 手机号 查找用户
     *
     * @param mobile 手机号
     * @return 用户
     */
    User findByMobile(String mobile);

}
