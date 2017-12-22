package com.test.majun.shiro.ch06.dao;

import com.test.majun.shiro.ch06.entity.User;

import java.util.Set;

/**
 * Created by majun on 20/12/2017.
 */
public interface UserDao {

    User createUser(User user);

    void updateUser(User user);

    void deleteUser(Long userId);

    void correlationRoles(Long userId,Long... roleIds);

    void uncorrelationRoles(Long userId,Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

}
