package com.test.majun.shiro.ch06.dao.impl;

import com.test.majun.shiro.ch06.dao.RoleDao;
import com.test.majun.shiro.ch06.entity.Role;
import com.test.majun.shiro.ch06.utils.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.util.Arrays;

/**
 * Created by majun on 20/12/2017.
 */
public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public Role createRole(Role role) {
        final String sql = "insert into sys_roles(role,description,available) values (?,?,?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement psst = connection.prepareStatement(sql,new String[] {"id"});
            psst.setString(1,role.getRole());
            psst.setString(2,role.getDescription());
            psst.setBoolean(3,role.getAvailable());
            return psst;
        }, holder);

        role.setId(holder.getKey().longValue());
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {
        String sql = "delete from sys_users_roles where role_id = ?";
        jdbcTemplate.update(sql,roleId);

        sql = "delete from sys_roles where id = ?";
        jdbcTemplate.update(sql,roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if(roleId == null || permissionIds==null || permissionIds.length==0){
            return;
        }
        String sql = "insert into sys_roles_permissions(role_id,permission_id) values (?,?)";
        Arrays.stream(permissionIds).forEach(permissionId->{
          if(!exists(roleId,permissionId)){
            jdbcTemplate.update(sql,roleId,permissionId);
          }
        });
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if(roleId == null || permissionIds==null || permissionIds.length==0){
            return;
        }
        String sql = "delete from sys_roles_permissions where role_id=? and permission_id=?";
        Arrays.stream(permissionIds).forEach(permissionId->{
            jdbcTemplate.update(sql,roleId,permissionId);
        });
    }

    private boolean exists(Long roleId,Long permissionId){
        String sql = "select count(1) from sys_roles_permissions where role_id= ? and permission_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,roleId,permissionId);
        return count != 0;
    }

}
