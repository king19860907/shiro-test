package com.test.majun.shiro.ch06.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by majun on 22/12/2017.
 */
public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return "a";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return new SimpleAuthenticationInfo("zhang","123",getName());
    }
}
