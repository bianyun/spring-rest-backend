package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.service.api.dto.sys.*;
import com.silentcloud.springrest.service.api.module.BaseService;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

/**
 * 用户服务接口
 *
 * @author bianyun
 */
public interface UserService extends BaseService<Long, User, UserDto> {

    /**
     * 根据 用户名 查找用户
     *
     * @param username 用户名
     * @return 用户DTO
     */
    @Nullable
    UserDto findByUsername(String username);

    /**
     * 根据 email 查找用户
     *
     * @param email Email
     * @return 用户DTO
     */
    @Nullable
    UserDto findByEmail(String email);

    /**
     * 根据 手机号 查找用户
     *
     * @param mobile 手机号
     * @return 用户DTO
     */
    @Nullable
    UserDto findByMobile(String mobile);

    /**
     * 检查用户的密码是否正确
     *
     * @param id            用户ID
     * @param plainPassword 明文密码
     * @return 密码是否正确
     */
    boolean isPasswordValid(Long id, String plainPassword);

    /**
     * 设置用户的密码
     *
     * @param id            用户ID
     * @param plainPassword 明文密码
     */
    void setPasswordById(Long id, String plainPassword);

    /**
     * 重设用户密码为默认密码
     *
     * @param id 用户ID
     */
    void resetPasswordById(Long id);

    /**
     * 根据 用户ID 查询用户的所有角色
     *
     * @param userId 用户ID
     * @return 用户的所有角色
     */
    List<RoleDto> getRolesByUserId(Long userId);

    /**
     * 根据 用户ID 查询用户的所有接口权限
     *
     * @param userId 用户ID
     * @return 用户的所有接口权限
     */
    Set<ApiPermDto> getApiPermsByUserId(Long userId);

    /**
     * 根据 用户ID 查询用户的所有菜单权限
     *
     * @param userId 用户ID
     * @return 用户的所有菜单权限
     */
    Set<MenuDto> getMenuPermsByUserId(Long userId);

    /**
     * 根据 用户ID 查询用户的所有按钮权限
     *
     * @param userId 用户ID
     * @return 用户的所有按钮权限
     */
    Set<ButtonDto> getButtonPermsByUserId(Long userId);

    /**
     * 保存为用户分配的角色集合
     *
     * @param id      用户ID
     * @param roleIds 角色ID集合
     */
    void saveRoleIdsByUserId(Long id, Set<Long> roleIds);
}
