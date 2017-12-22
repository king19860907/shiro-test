package com.test.majun.shiro.ch06.dao.impl;

import com.test.majun.shiro.ch06.dao.PermissionDao;
import com.test.majun.shiro.ch06.entity.Permission;
import com.test.majun.shiro.ch06.utils.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;

/**
 * Created by majun on 20/12/2017.
 */
public class PermissionDaoImpl implements PermissionDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public Permission createPermission(Permission permission) {
        final String sql = "insert into sys_permissions(permission,description,available) values (?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement psst = connection.prepareStatement(sql,new String[]{"id"});
            psst.setString(1,permission.getPermission());
            psst.setString(2,permission.getDescription());
            psst.setBoolean(3,permission.getAvailable());
            return psst;
        },keyHolder);
        permission.setId(keyHolder.getKey().longValue());

        return permission;
    }

    @Override
    public void deletePermission(Long permissionId) {

        //首先把与permission关联的相关表的数据删掉
        String sql = "delete from sys_roles_permissions where permission_id = ?";
        jdbcTemplate.update(sql,permissionId);

        sql = "delete from sys_permissions where id = ?";
        jdbcTemplate.update(sql,permissionId);

    }
}
