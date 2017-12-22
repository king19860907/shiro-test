package com.test.majun.shiro.ch06.realm;

import com.test.majun.shiro.ch06.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by majun on 22/12/2017.
 */
public class MyRealm2 implements Realm {

    @Override
    public String getName() {
        return "c";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        User user = new User("zhang","123");
        return new SimpleAuthenticationInfo(user,"123",getName());
    }
}
