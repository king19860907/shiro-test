package com.test.majun.shiro.ch03;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by majun on 13/12/2017.
 */
public class PermissionTest extends BaseTest {

    @Test
    public void testIsPermitted(){

        login("classpath:shiro-permission.ini","zhang","123");

        //判断拥有权限: user:create
        Assert.assertEquals(true,subject().isPermitted("user:create"));

        //判断拥有权限: user:update and user:delete
        Assert.assertEquals(true,subject().isPermittedAll("user:create","user:delete"));

        //判断没有权限: user:view
        Assert.assertEquals(false,subject().isPermitted("user:view"));

    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission(){

        login("classpath:shiro-permission.ini","zhang","123");

        //断言拥有权限: user:create
        subject().checkPermission("user:create");

        //断言拥有权限: user:update and user:delete
        subject().checkPermissions("user:create","user:delete");

        //判断没有权限: user:view
        subject().checkPermission("user:view");

    }

    @Test
    public void testWildcardPermission1(){

        login("classpath:shiro-permission.ini","li","123");

        subject().checkPermissions("system:user:update","system:user:delete");
        subject().checkPermission("system:user:update,delete");
    }

    @Test
    public void testWildcardPermission2(){

        login("classpath:shiro-permission.ini","li","123");

        subject().checkPermission("system:user:create,update,delete,view");

        subject().checkPermission("system:user:*");

        subject().checkPermission("system:user");

    }

    @Test
    public void testWildcardPermission3(){

        login("classpath:shiro-permission.ini","li","123");

        subject().checkPermissions("user:view","root:view");

        subject().checkPermissions("system:user:view","root:root:view");

    }

    @Test
    public void testWildcardPermission4(){

        login("classpath:shiro-permission.ini","li","123");

        //role71
        subject().checkPermission("user:view:1");

        //role72
        subject().checkPermission("user:update,delete:1");
        subject().checkPermissions("user:update:1","user:delete:1");

        //role73
        subject().checkPermissions("user:add,view,delete,open:1");

        //role74
        subject().checkPermissions("user:auth:1,2");
        subject().checkPermissions("user:auth:1","user:auth:2");

        //role73
        subject().checkPermissions("user:view:1","user:auth:2");
    }

    @Test
    public void testWildcardPermission5(){

        login("classpath:shiro-permission.ini","li","123");

        //role81
        subject().checkPermissions("menu:delete","menu:delete:1");

        //role82
        subject().checkPermissions("organization:delete","organization:delete:1","organization:*");

    }

}
