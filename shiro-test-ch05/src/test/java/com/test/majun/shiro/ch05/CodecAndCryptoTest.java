package com.test.majun.shiro.ch05;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by majun on 19/12/2017.
 */
public class CodecAndCryptoTest {

    /**
     * Base64编码
     */
    @Test
    public void testBase64(){
        String str = "Hello";
        //编码
        String base64Encoded = Base64.encodeToString(str.getBytes());
        System.out.println(base64Encoded);
        //解码
        String str2 = Base64.decodeToString(base64Encoded.getBytes());
        System.out.println(str2);
        Assert.assertEquals(str,str2);
    }

    /**
     * 16进制编码
     */
    @Test
    public void testHex(){
        String str = "Hello";
        String encoded = Hex.encodeToString(str.getBytes());
        System.out.println(encoded);
        String str2 = new String(Hex.decode(encoded));
        System.out.println(str2);
        Assert.assertEquals(str,str2);
    }

    /**
     * 编码转换
     */
    @Test
    public void testCodecSupport(){
        String str = "hello";
        byte[] bytes = CodecSupport.toBytes(str,"utf-8");
        System.out.println(bytes);
        String str2 = CodecSupport.toString(bytes,"utf-8");
        System.out.println(str2);
    }

    /**
     * 生成随机数
     */
    @Test
    public void testRandom(){
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());
        System.out.println(randomNumberGenerator.nextBytes().toHex());
    }

    /**
     * MD5编码
     */
    @Test
    public void testMD5(){
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str,salt).toString();
        //String md5 = new Md5Hash(str,salt,2).toString(); 定义循环次数
        System.out.println(md5);
    }

    @Test
    public void testSha1(){
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha1Hash(str,salt).toString();
        System.out.println(sha1);
    }

    @Test
    public void testSha256(){
        String str = "hello";
        String salt = "123";
        String sha256 = new Sha256Hash(str,salt).toString();
        System.out.println(sha256);
    }

    @Test
    public void testSha384(){
        String str = "hello";
        String salt = "123";
        String sha384 = new Sha384Hash(str,salt).toString();
        System.out.println(sha384);
    }

    @Test
    public void testSha512(){
        String str = "hello";
        String salt = "123";
        String sha512 = new Sha512Hash(str,salt).toString();
        System.out.println(sha512);
    }

    /**
     * 通用散列支持
     */
    @Test
    public void testSimpleHash(){
        String str = "hello";
        String salt = "123";
        String simpleHash = new SimpleHash("SHA-1",str,salt).toString();
        System.out.println(simpleHash);
    }

    @Test
    public void testHashService(){
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");//默认算法SHA-512
        hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐,默认无
        hashService.setGeneratePublicSalt(true); //是否生成公盐,默认false
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator()); //用于生成公盐,默认就这个
        hashService.setHashIterations(1);

        HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
                .setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }

    /**
     * 64位jdk不支持(AES/CBC128/PKCS5Padding)
     * 参考https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html
     * Aes加解密
     */
    @Test
    public void testAesCipherservice() throws NoSuchPaddingException, NoSuchAlgorithmException {
       /* AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setBlockSize(128); //设置key长度
        Key key = aesCipherService.generateNewKey();

        String text = "hello";

        //加密
        String encrptText = aesCipherService.encrypt(text.getBytes(),key.getEncoded()).toHex();
        System.out.println(encrptText);

        //解密
        String text2 = new String(aesCipherService.decrypt(encrptText.getBytes(),key.getEncoded()).getBytes());
        System.out.println(text2);

        Assert.assertEquals(text,text2);*/
        //String transformationString = "AES/CBC128/PKCS5Padding";
        String transformationString = "DES/CBC/PKCS5Padding";
        Cipher.getInstance(transformationString);

    }

    /**
     * 64位jdk不支持(Blowfish/CBC128/PKCS5Padding)
     * 参考https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html
     * Aes加解密
     */
    @Test
    public void testBlowfishCipherService(){
        BlowfishCipherService blowfishCipherService = new BlowfishCipherService();
        blowfishCipherService.setBlockSize(128);

        Key key = blowfishCipherService.generateNewKey();
        String text = "hello";

        //加密
        String encrptText = blowfishCipherService.encrypt(text.getBytes(),key.getEncoded()).toHex();
        System.out.println(encrptText);

        //解密
        String text2 = new String(blowfishCipherService.decrypt(Hex.decode(encrptText),key.getEncoded()).getBytes());
        System.out.println(text2);
    }

    @Test
    public void testDefaultBlockCipherService() throws Exception {


        //对称加密，使用Java的JCA（javax.crypto.Cipher）加密API，常见的如 'AES', 'Blowfish'
        DefaultBlockCipherService cipherService = new DefaultBlockCipherService("AES");
        cipherService.setKeySize(128);

        //生成key
        Key key = cipherService.generateNewKey();

        String text = "hello";

        //加密
        String encrptText = cipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        System.out.println(encrptText);
        //解密
        String text2 = new String(cipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
        System.out.println(text2);

        Assert.assertEquals(text, text2);
    }


}
