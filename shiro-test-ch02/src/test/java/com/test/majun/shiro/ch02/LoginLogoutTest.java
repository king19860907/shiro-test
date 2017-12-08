package com.test.majun.shiro.ch02;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by majun on 09/12/2017.
 */
public class LoginLogoutTest {

    @Test
    public void testHelloWorld(){

        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager =factory.getInstance();
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到 Subject 及创建用户名/密码身份验证 Token(即用户身份/凭证)
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try{
            //4、登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e){
            //5、身份验证失败
            e.printStackTrace();
        }

        Assert.assertEquals(true,subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();

    }

    @Test
    public void testCustomRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try{
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();
    }

    /**
     * securityManager 会按照 realms 指定的顺序进行身份认证
     * 如果删除“securityManager.realms=$myRealm1,$myRealm2”
     * 那 么 securityManager 会按照 realm 声明的顺序进行使用(即无需设置 realms 属性，其会自动 发现)
     * 当我们显示指定 realm 后，其他没有指定 realm 将被忽略
     */
    @Test
    public void testCustomMultiRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("wang","12345");

        try{
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();
    }

    @Test
    public void testJDBCRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try{
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();
    }


}
