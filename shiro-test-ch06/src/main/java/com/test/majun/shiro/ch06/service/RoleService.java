package com.test.majun.shiro.ch06.service;

import com.test.majun.shiro.ch06.entity.Role;

/**
 * Created by majun on 20/12/2017.
 */
public interface RoleService {

    Role createRole(Role role);

    void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    void correlationPermissions(Long roleId,Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    void uncorrelationPermissions(Long roleId,Long... permissionIds);

}
