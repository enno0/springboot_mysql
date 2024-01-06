package com.enno.DAO;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enno.models.UserRole;

@Service
public interface UserRoleDAO {
    List<UserRole> getAllUsers();

    UserRole getUserById(int id);

    void addUserInf(UserRole user);

    int updateUserInf(UserRole user);

    int deleteUser(int id);

}
