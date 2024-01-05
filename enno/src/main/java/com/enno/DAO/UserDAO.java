package com.enno.DAO;

import java.util.List;

import com.enno.models.User;

public interface UserDAO {
    User getUserById(int id);

    void addUserInf(User user);

    int updateUserInf(User user);

    int deleteUser(int id);

    List<User> getAllUsers();

    String getUserByName(String name);

    String getUserB();

    List<String> getUserRoles();

}
