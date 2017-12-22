package com.test.majun.shiro.ch06.service;

import com.test.majun.shiro.ch06.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by majun on 22/12/2017.
 */
public class ServiceTest extends BaseTest {

    @Test
    public void testUserRolePermissionRelation(){

        //zhang
        Set<String> roles = userService.findRoles(u1.getUsername());
        Assert.assertEquals(1,roles.size());
        Assert.assertTrue(roles.contains(r1.getRole()));

        Set<String> permissions = userService.findPermissions(u1.getUsername());
        Assert.assertEquals(3,permissions.size());
        Assert.assertTrue(permissions.contains(p1.getPermission()));

        //li
        roles =userService.findRoles(u2.getUsername());
        Assert.assertEquals(0,roles.size());
        permissions = userService.findPermissions(u2.getUsername());
        Assert.assertEquals(0,permissions.size());

        //解除 admin-menu:update
        roleService.uncorrelationPermissions(r1.getId(),p3.getId());
        permissions = userService.findPermissions(u1.getUsername());
        Assert.assertEquals(2,permissions.size());
        Assert.assertFalse(permissions.contains(p3.getPermission()));

        //删除一个permission
        permissionService.deletePermission(p2.getId());
        permissions = userService.findPermissions(u1.getUsername());
        Assert.assertEquals(1,permissions.size());

        //解除zhang-admin关联
        userService.uncorrelationRoles(u1.getId(),r1.getId());
        roles = userService.findRoles(u1.getUsername());
        Assert.assertEquals(0,roles.size());
        permissions = userService.findPermissions(u1.getUsername());
        Assert.assertEquals(0,permissions.size());

    }

}
