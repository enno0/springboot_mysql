package com.enno.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enno.models.User;
import com.enno.rowmapper.UserRowMapper;

@Primary
@Repository
public class UserDaoImp implements UserDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImp.class);

    public UserDaoImp(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addUserInf(User user) {
        try {
            String sql = "INSERT INTO `Users`(`UserName`, `Password`, `FullName`, `Enabled`) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getFullName(), user.getEnabled());

            // Use logging instead of System.out.println for production code
            logger.info("Student saved!");
        } catch (DataAccessException e) {
            // Handle exception (e.g., log, throw custom exception, etc.)
            logger.error("Error saving student", e);
        }

    }

    @Override
    public int updateUserInf(User user) {
        try {
            String sql = "UPDATE `Users` SET `UserName`=?, `Password`=?, `FullName`=?, `Enabled`=? WHERE `id`=?";
            return jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getFullName(),
                    user.getEnabled(), user.getId());
        } catch (DataAccessException e) {
            logger.error("Error updating user", e);
            return -1; // Or throw a custom exception
        }
    }

    @Override

    public User getUserById(int id) {
        String sql = "SELECT * FROM `Users` WHERE `id`=?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new UserRowMapper());
    }

    @Override
    public int deleteUser(int id) {
        String sql = "DELETE FROM `Users` WHERE `id`=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM `Users`";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

}
