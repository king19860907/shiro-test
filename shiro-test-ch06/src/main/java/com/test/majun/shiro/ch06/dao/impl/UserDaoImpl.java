package com.test.majun.shiro.ch06.dao.impl;

import com.test.majun.shiro.ch06.dao.UserDao;
import com.test.majun.shiro.ch06.entity.User;
import com.test.majun.shiro.ch06.utils.JdbcTemplateUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.CollectionUtils;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by majun on 20/12/2017.
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public User createUser(User user) {
        final String sql = "insert into sys_users(username, password, salt, locked) values(?,?,?, ?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement psst = connection.prepareStatement(sql,new String[]{"id"});
            psst.setString(1, user.getUsername());
            psst.setString(2, user.getPassword());
            psst.setString(3, user.getSalt());
            psst.setBoolean(4, user.getLocked());
            return psst;
        }, holder);

        user.setId(holder.getKey().longValue());
        return null;
    }

    @Override
    public void updateUser(User user) {
        String sql = "update sys_users set username=?, password=?, salt=?, locked=? where id=?";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getSalt(),user.getLocked(),user.getId());
    }

    @Override
    public void deleteUser(Long userId) {
        String sql = "delete from sys_users where id=?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if(userId == null || roleIds == null || roleIds.length==0){
            return;
        }
        String sql = "insert into sys_users_roles(user_id, role_id) values(?,?)";
        Arrays.stream(roleIds).forEach(roleId->{
            if(!exists(userId,roleId)){
                jdbcTemplate.update(sql,userId,roleId);
            }
        });
    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        if(userId == null || roleIds == null || roleIds.length==0){
            return;
        }
        String sql = "delete from sys_users_roles where user_id=? and role_id=?";
        Arrays.stream(roleIds).forEach(roleId->{
            jdbcTemplate.update(sql,userId,roleId);
        });
    }

    private boolean exists(Long userId, Long roleId) {
        String sql = "select count(1) from sys_users_roles where user_id=? and role_id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId, roleId) != 0;
    }

    @Override
    public User findOne(Long userId) {
        String sql = "select id, username, password, salt, locked from sys_users where id=?";
        List<User> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),userId);
        return CollectionUtils.isEmpty(list)?null:list.get(0);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select id, username, password, salt, locked from sys_users where username=?";
        List<User> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),username);
        return CollectionUtils.isEmpty(list)?null:list.get(0);    }

    @Override
    public Set<String> findRoles(String username) {
        String sql = "select role from sys_users u, sys_roles r,sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
        return new HashSet<>(jdbcTemplate.queryForList(sql,String.class,username));
    }

    @Override
    public Set<String> findPermissions(String username) {
        String sql = "select permission from sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp where u.username=? and u.id=ur.user_id and r.id=ur.role_id and r.id=rp.role_id and p.id=rp.permission_id";
        return new HashSet<>(jdbcTemplate.queryForList(sql,String.class,username));
    }
}
