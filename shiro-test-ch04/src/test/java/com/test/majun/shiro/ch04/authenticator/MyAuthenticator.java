package com.test.majun.shiro.ch04.authenticator;

import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Arrays;
import java.util.Set;
import java.util.Map;

/**
 * Created by majun on 19/12/2017.
 */
public class MyAuthenticator extends ModularRealmAuthenticator {

    public void setBytes(byte[] bytes){
        System.out.println(new String(bytes));
    }

    public void setArray(int[] ints){
        System.out.println(Arrays.toString(ints));
    }

    public void setSet(Set<Realm> realms){
        System.out.println(realms);
    }

    public void setMap(Map<Object,Object> maps){
        System.out.println(maps);
        System.out.println(maps.get("1"));
    }

}
