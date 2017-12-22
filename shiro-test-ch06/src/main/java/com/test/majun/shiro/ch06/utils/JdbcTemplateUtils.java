package com.test.majun.shiro.ch06.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by majun on 20/12/2017.
 */
public class JdbcTemplateUtils {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate(){
        if(jdbcTemplate == null){
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate(){

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.11.212:3306/shiro-test");
        ds.setUsername("root");
        ds.setPassword("123456");

        return new JdbcTemplate(ds);
    }

}
