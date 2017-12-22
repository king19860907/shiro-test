package com.test.majun.shiro.ch06.service.impl;

import com.test.majun.shiro.ch06.dao.RoleDao;
import com.test.majun.shiro.ch06.dao.impl.RoleDaoImpl;
import com.test.majun.shiro.ch06.entity.Role;
import com.test.majun.shiro.ch06.service.RoleService;

/**
 * Created by majun on 20/12/2017.
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId,permissionIds);
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId,permissionIds);
    }
}
