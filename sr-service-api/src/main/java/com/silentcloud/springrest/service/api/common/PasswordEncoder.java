package com.silentcloud.springrest.service.api.common;

public interface PasswordEncoder {

    /**
     * 加密明文密码
     *
     * @param plainPassword 明文密码
     * @return 加密后的密码
     */
    String encode(String plainPassword);

    /**
     * 验证明文密码和已加密过的密码是否匹配
     *
     * @param plainPassword   明文密码
     * @param encodedPassword 加密后的密码
     * @return <code>true</code> 如果匹配，否则返回 <code>false</code>
     */
    boolean matches(String plainPassword, String encodedPassword);

}
