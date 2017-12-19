package com.test.majun.shiro.ch05;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * Created by majun on 13/12/2017.
 */
public class BaseTest {

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }

    protected void login(String config,String username,String password){

        Factory<SecurityManager> factory = new IniSecurityManagerFactory(config);
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken(username,password);
        subject.login(token);

    }

    public Subject subject(){
        return SecurityUtils.getSubject();
    }

}
