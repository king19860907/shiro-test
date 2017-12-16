package com.test.majun.shiro.ch03;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by majun on 16/12/2017.
 */
@SuppressWarnings("ALL")
public class AuthorizerTest extends BaseTest {

    @Test
    public void testIsPermitted(){
        login("classpath:shiro-authorizer.ini","zhang","123");

        //判断拥有权限
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));

        //通过二进制的方式表示权限
        Assert.assertTrue(subject().isPermitted("+user1+2")); //新增权限
        Assert.assertTrue(subject().isPermitted("+user1+8")); //查看权限
        Assert.assertTrue(subject().isPermitted("+user1+10")); //新增及查看

        Assert.assertFalse(subject().isPermitted("+user1+4"));

        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }

    @Test
    public void testIsPermitted2(){
        login("classpath:shiro-jdbc-authorizer.ini","zhang","123");

        //判断拥有权限
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));

        //通过二进制的方式表示权限
        Assert.assertTrue(subject().isPermitted("+user1+2")); //新增权限
        Assert.assertTrue(subject().isPermitted("+user1+8")); //查看权限
        Assert.assertTrue(subject().isPermitted("+user1+10")); //新增及查看

        Assert.assertFalse(subject().isPermitted("+user1+4"));

        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }

}
