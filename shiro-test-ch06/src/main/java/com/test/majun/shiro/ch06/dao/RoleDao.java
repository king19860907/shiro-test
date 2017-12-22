package com.test.majun.shiro.ch06.dao;

import com.test.majun.shiro.ch06.entity.Role;

/**
 * Created by majun on 20/12/2017.
 */
public interface RoleDao {

    Role createRole(Role role);

    void deleteRole(Long roleId);

    void correlationPermissions(Long roleId,Long... permissionIds);

    void uncorrelationPermissions(Long roleId,Long... permissionIds);

}
