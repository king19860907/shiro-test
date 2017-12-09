package com.test.majun.shiro.ch02;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by majun on 09/12/2017.
 */
public class AuthenticatorTest {

    /**
     * 首先通用化登录逻辑
     */
    private void login(String config){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(config);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();


        AuthenticationToken token = new UsernamePasswordToken("zhang","123");
        subject.login(token);
    }

    @Test
    public void AllSuccessfulStrategy(){
        login("classpath:shiro-authenticator-all-success.ini");

        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了 Realm 验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        System.out.println(principalCollection.asList());
        Assert.assertEquals(2,principalCollection.asList().size());
    }

    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail(){
        login("classpath:shiro-authenticator-all-fail.ini");
        Subject subject = SecurityUtils.getSubject();
    }

    @Test
    public void testAtLeastOneSuccessfulStrategyWithSuccess(){
        login("classpath:shiro-authenticator-atLeastOne-success.ini");
        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();
        System.out.println(principalCollection.asList());
        Assert.assertEquals(2,principalCollection.asList().size());
    }

    @Test
    public void FirstSuccessfulStrategy(){
        login("classpath:shiro-authenticator-first-success.ini");
        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();
        System.out.println(principalCollection.asList());
        Assert.assertEquals(1,principalCollection.asList().size());
    }

    @Test
    public void OnlyOneSuccessfulStrategy(){
        login("classpath:shiro-authenticator-onlyone-success.ini");
        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();
        System.out.println(principalCollection.asList());
        Assert.assertEquals(2,principalCollection.asList().size());

    }

    @Test
    public  void AtLeastTwoSuccessfulStrategy(){
        login("classpath:shiro-authenticator-atLeastTwo-success.ini");
        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();
        System.out.println(principalCollection.asList());
        Assert.assertEquals(2,principalCollection.asList().size());
    }

}
