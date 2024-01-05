package com.enno.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.enno.models.UserRole;

public class UserRoleRowM implements RowMapper<UserRole> {

    @Override
    @Nullable
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setUser_Role_Id(rs.getInt("User_Role_Id"));
        userRole.setUserName(rs.getString("UserName"));
        userRole.setRole(rs.getString("Role"));
        return userRole;
    }

}
