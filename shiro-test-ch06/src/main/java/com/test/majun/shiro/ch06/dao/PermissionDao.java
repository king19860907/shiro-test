package com.test.majun.shiro.ch06.dao;

import com.test.majun.shiro.ch06.entity.Permission;

/**
 * Created by majun on 20/12/2017.
 */
public interface PermissionDao {

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);

}
