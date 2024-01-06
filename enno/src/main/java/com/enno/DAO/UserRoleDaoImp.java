package com.enno.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enno.models.UserRole;
import com.enno.rowmapper.UserRoleRowM;

@Primary
@Repository
@Service
public class UserRoleDaoImp implements UserRoleDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserRoleDaoImp.class);

    public UserRoleDaoImp(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addUserInf(UserRole user) {
        try {
            String sql = "INSERT INTO `User_Roles`(`UserName`, `Role`) VALUES (?,?)";
            jdbcTemplate.update(sql, user.getUserName(), user.getRole());

            // Use logging instead of System.out.println for production code
            logger.info("UserNam and Role saved!");
        } catch (DataAccessException e) {
            // Handle exception (e.g., log, throw custom exception, etc.)
            logger.error("Error saving UserNam and Role", e);
        }

    }

    @Override
    public int deleteUser(int id) {
        String sql = "DELETE FROM `User_Roles` WHERE `User_Role_Id`=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<UserRole> getAllUsers() {
        String sql = "SELECT * FROM `User_Roles`";
        return jdbcTemplate.query(sql, new UserRoleRowM());
    }

    @Override
    public UserRole getUserById(int id) {
        String sql = "SELECT * FROM `User_Roles`WHERE `User_Role_Id`=?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new UserRoleRowM());
    }

    @Override
    public int updateUserInf(UserRole user) {
        try {
            String sql = "UPDATE `User_Roles` SET `UserName`=?, `Role`=? WHERE `User_Role_Id`=?";
            return jdbcTemplate.update(sql, user.getUserName(), user.getRole(), user.getUser_Role_Id());
        } catch (DataAccessException e) {
            logger.error("Error updating user", e);
            return -1; // Or throw a custom exception
        }
    }

}
