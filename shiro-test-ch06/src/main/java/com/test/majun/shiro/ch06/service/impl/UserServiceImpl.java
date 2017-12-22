package com.test.majun.shiro.ch06.service.impl;

import com.test.majun.shiro.ch06.dao.UserDao;
import com.test.majun.shiro.ch06.dao.impl.UserDaoImpl;
import com.test.majun.shiro.ch06.entity.User;
import com.test.majun.shiro.ch06.service.PasswordHelper;
import com.test.majun.shiro.ch06.service.UserService;

import java.util.Set;

/**
 * Created by majun on 20/12/2017.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    private PasswordHelper passwordHelper = new PasswordHelper();

    @Override
    public User createUser(User user) {
        passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    @Override
    public void changePassword(Long userId, String password) {
        User user = userDao.findOne(userId);
        if(user != null){
            user.setPassword(password);
            passwordHelper.encryptPassword(user);
            userDao.updateUser(user);
        }
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.correlationRoles(userId,roleIds);
    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        userDao.uncorrelationRoles(userId,roleIds);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }
}
