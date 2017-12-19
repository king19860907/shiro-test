package com.test.majun.shiro.ch05;

import junit.framework.Assert;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

/**
 * Created by majun on 19/12/2017.
 */
public class PasswordTest extends BaseTest {

    @Test
    public void testPasswordServiceWithMyRealm(){
        login("classpath:shiro-passwordservice.ini","wu","123");
    }

    @Test
    public void testPasswordServiceWithJdbcRealm(){
        login("classpath:shiro-jdbc-passwordservice.ini","wu","123");
    }

    @Test
    public void testGeneratePassword(){
        String algorithmName = "md5";
        String username="liu";
        String password="123";
        String salt1=username;
        String salt2=new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName,password,salt1+salt2,hashIterations);
        String encodePassword=hash.toHex();
        System.out.println(salt2);
        System.out.println(encodePassword);
    }

    @Test
    public void testHashedCredentialsMatcherWithMyRealm2() {
        login("classpath:shiro-hashedCredentialsMatcher.ini","liu","123");
    }

    @Test
    public void testHashedCredentialsMatcherWithJdbcRealm(){
        /**
         *  Shiro 默认使用了 apache commons BeanUtils，默认是不进行 Enum 类型转型 的，此时需要自己注册一个 Enum 转换器
         */
        BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);

        //使用testGeneratePassword生成的散列密码
        login("classpath:shiro-jdbc-hashedCredentialsMatcher.ini", "liu", "123");
        Assert.assertTrue(subject().isAuthenticated());
    }

    private class EnumConverter extends AbstractConverter{

        @Override
        protected String convertToString(Object value) throws Throwable {
            Enum enumValue = (Enum)value;
            return enumValue.name();
        }

        @Override
        protected Object convertToType(Class type, Object value) throws Throwable {
            return Enum.valueOf(type,value.toString());
        }

        @Override
        protected Class getDefaultType() {
            return null;
        }
    }

    @Test()
    public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
        for(int i = 1; i <= 5; i++) {
            try {
                login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
            } catch (Exception e) {/*ignore*/}
        }
        try{
            login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
        }catch (Exception e){
            e.printStackTrace();
        }
        login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "123");
    }

}
