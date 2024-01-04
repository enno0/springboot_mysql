package com.enno.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.enno.models.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    @Nullable
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setEnabled(rs.getInt("Enabled"));
        user.setUserName(rs.getString("UserName"));
        user.setPassword(rs.getString("Password"));
        user.setFullName(rs.getString("FullName"));
        user.setId(rs.getInt("id"));
        return user;
    }

}
