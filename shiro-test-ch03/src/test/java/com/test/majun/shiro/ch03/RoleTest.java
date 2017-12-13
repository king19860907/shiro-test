package com.test.majun.shiro.ch03;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by majun on 13/12/2017.
 */
public class RoleTest extends BaseTest {

    @Test
    public void testHasRole(){
        login("classpath:shiro-role.ini","zhang","123");

        //判断拥有角色roel1
        Assert.assertEquals(true,subject().hasRole("role1"));

        //判断拥有角色role1 and role2
        Assert.assertEquals(true,subject().hasAllRoles(Arrays.asList("role1","role2")));

        //判断拥有角色role1 and role2 and role3
        boolean [] results = subject().hasRoles(Arrays.asList("role1","role2","role3"));
        Assert.assertTrue(results[0]);
        Assert.assertTrue(results[1]);
        Assert.assertFalse(results[2]);
    }

    /**
     * Shiro 提供的 checkRole/checkRoles 和 hasRole/hasAllRoles 不同的地方是它在判断为假的情 况下会抛出 UnauthorizedException 异常
     */
    @Test(expected = UnauthorizedException.class)
    public void testCheckRole(){
        login("classpath:shiro-role.ini","zhang","123");

        //断言拥有角色:role1
        subject().checkRole("role1");

        //断言拥有角色:role1 and role3  失败抛出异常
        subject().checkRoles("role1","role3");
    }

}
