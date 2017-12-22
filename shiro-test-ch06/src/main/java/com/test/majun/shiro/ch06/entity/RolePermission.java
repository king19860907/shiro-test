package com.test.majun.shiro.ch06.entity;

/**
 * Created by majun on 20/12/2017.
 */
public class RolePermission {

    private Long roleId;

    private Long permissionId;

    @Override
    public String toString() {
        return "RolePermission{" +
                "permissionId=" + permissionId +
                ", roleId=" + roleId +
                '}';
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
