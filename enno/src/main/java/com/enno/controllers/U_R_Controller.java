package com.enno.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enno.DAO.UserRoleDAO;
import com.enno.dto.UserRoleDto;
import com.enno.models.UserRole;

@Controller
@RequestMapping("/User_Role")

public class U_R_Controller {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @GetMapping("/showUserR")
    public String showUserstInfo(Model model) {
        List<UserRole> userR = userRoleDAO.getAllUsers();
        model.addAttribute("userR", userR);
        return "userR-list";
    }

    @GetMapping("/addUserR")
    public String addUserR(Model model) {
        model.addAttribute("UserR", new UserRoleDto());
        return "add-userR";
    }

    @PostMapping("/saveUserR")
    public String saveUserR(UserRole userR) {
        userRoleDAO.addUserInf(userR);
        return "redirect:/User_Role/showUserR";
    }

    @GetMapping("/editUser/{id}")
    public String editUserR(@PathVariable int id, Model model) {
        UserRole user = userRoleDAO.getUserById(id);
        model.addAttribute("User", user);
        return "edit-UserR";
    }

    @PostMapping("/updateUserR")
    public String updateUserR(UserRole user) {
        userRoleDAO.updateUserInf(user);
        return "redirect:/User_Role/showUserR";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserR(@PathVariable int id) {
        userRoleDAO.deleteUser(id);
        return "redirect:" + "/User_Role/showUserR";
    }

}
