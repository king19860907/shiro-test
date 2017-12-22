package com.test.majun.shiro.ch06.service.impl;

import com.test.majun.shiro.ch06.dao.PermissionDao;
import com.test.majun.shiro.ch06.dao.impl.PermissionDaoImpl;
import com.test.majun.shiro.ch06.entity.Permission;
import com.test.majun.shiro.ch06.service.PermissionService;

/**
 * Created by majun on 20/12/2017.
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
