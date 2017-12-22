package com.test.majun.shiro.ch06.realm;

import com.test.majun.shiro.ch06.entity.User;
import com.test.majun.shiro.ch06.service.UserService;
import com.test.majun.shiro.ch06.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by majun on 22/12/2017.
 */
public class UserRealm extends AuthorizingRealm{

    private UserService userService = new UserServiceImpl();

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String)principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));

        return authorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();
        User user = userService.findByUsername(username);

        if(user == null){
            throw new UnknownAccountException();
        }

        if(Boolean.TRUE.equals(user.getLocked())){
            throw new LockedAccountException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,
                user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),getName());

        return authenticationInfo;
    }



}
