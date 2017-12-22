package com.test.majun.shiro.ch06.service;

import com.test.majun.shiro.ch06.entity.User;

import java.util.Set;

/**
 * Created by majun on 20/12/2017.
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     * @return
     */
    User createUser(User user);

    /**
     * 修改密码
     * @param userId
     * @param password
     */
    void changePassword(Long userId,String password);

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    void correlationRoles(Long userId,Long... roleIds);

    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    void uncorrelationRoles(Long userId,Long... roleIds);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);

}
