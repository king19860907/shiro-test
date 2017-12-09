package com.test.majun.shiro.ch02.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by majun on 09/12/2017.
 */
public class MyRealm4 implements Realm {
    public String getName() {
        return "myrealm4";
    }

    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持 UsernamePasswordToken 类型的 Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString(); //得到用户名
        String password = new String((char[])authenticationToken.getCredentials()); //得到密码
        if(!"zhang".equals(username)){
            throw new UnknownAccountException();    //如果用户名错误
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();  //如果密码错误
        }
        //如果身份认证验证成功，返回一个 AuthenticationInfo 实现;
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
