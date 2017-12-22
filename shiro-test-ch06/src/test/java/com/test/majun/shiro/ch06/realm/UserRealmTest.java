package com.test.majun.shiro.ch06.realm;

import com.test.majun.shiro.ch06.BaseTest;
import junit.framework.Assert;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.Test;

/**
 * Created by majun on 22/12/2017.
 */
public class UserRealmTest extends BaseTest {

    @Test
    public void testLoginSuccess(){
        login("classpath:shiro.ini",u1.getUsername(),password);
        Assert.assertTrue(subject().isAuthenticated());
    }

    @Test(expected = UnknownAccountException.class)
    public void testLoginFailWithUnknownUserName(){
        login("classpath:shiro.ini",u1.getUsername()+"aaa",password);
    }

    @Test(expected = IncorrectCredentialsException.class)
    public void testLoginFailWithErrorPassword(){
        login("classpath:shiro.ini",u1.getUsername(),password+"aaa");
    }

    @Test(expected = LockedAccountException.class)
    public void testLoginFailWithLocked(){
        login("classpath:shiro.ini",u4.getUsername(),password);
    }

    @Test(expected = ExcessiveAttemptsException.class)
    public void testLoginFailWithLimitRetryCount(){
        for(int i = 1 ;i<=5;i++){
            try{
                login("classpath:shiro.ini",u3.getUsername(),password+"93");
            }catch (Exception e){

            }
        }
        login("classpath:shiro.ini",u3.getUsername(),password);
    }

    @Test
    public void testHasRole(){
        login("classpath:shiro.ini",u1.getUsername(),password);
        Assert.assertTrue(subject().hasRole("admin"));
    }

    @Test
    public void testHasNoRole(){
        login("classpath:shiro.ini",u1.getUsername(),password);
        Assert.assertFalse(subject().hasRole("user"));
    }

    @Test
    public void hasPermission(){
        login("classpath:shiro.ini",u1.getUsername(),password);
        Assert.assertTrue(subject().isPermittedAll("user:create","menu:create","user:update"));
    }

    @Test
    public void hasNoPermission(){
        login("classpath:shiro.ini",u2.getUsername(),password);
        Assert.assertFalse(subject().isPermitted("user:create"));
    }

}
