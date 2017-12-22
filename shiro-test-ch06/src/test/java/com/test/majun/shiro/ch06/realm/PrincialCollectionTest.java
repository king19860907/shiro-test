package com.test.majun.shiro.ch06.realm;

import com.test.majun.shiro.ch06.BaseTest;
import com.test.majun.shiro.ch06.entity.User;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;
import java.util.List;

/**
 * Created by majun on 22/12/2017.
 */
public class PrincialCollectionTest extends BaseTest {

    @Test
    public void test(){
        //因为Realm里没有进行验证，所以相当于每个Realm都身份验证成功了
        login("classpath:shiro-multirealm.ini", "zhang", "123");

        Subject subject = subject();

        Object primaryPrincipal1 = subject.getPrincipal();
        PrincipalCollection principalCollection = subject.getPrincipals();
        Object primaryPrincipal2 = principalCollection.getPrimaryPrincipal();

        //但是因为多个Realm都返回了Principal，所以此处到底是哪个是不确定的
        Assert.assertEquals(primaryPrincipal1,primaryPrincipal2);

        Set<String> realmNames = principalCollection.getRealmNames();
        System.out.println(realmNames);

        //因为MyRealm1和MyRealm2返回的凭据都是zhang，所以排重了
        Set<Object> set = principalCollection.asSet();
        List<Object> list = principalCollection.asList();
        System.out.println(set);
        System.out.println(list);

        //根据Realm名字获取
        Collection<User> users = principalCollection.fromRealm("c");
        System.out.println(users);

    }

}
