package com.test.majun.shiro.ch04;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by majun on 19/12/2017.
 */
public class IniMainTest {

    @Test
    public void test(){

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-config-main.ini");

        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken("zhang","123");
        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());

    }

}
