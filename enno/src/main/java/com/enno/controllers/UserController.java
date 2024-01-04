package com.enno.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enno.DAO.UserDAO;
import com.enno.dto.UserDTO;
import com.enno.models.User;

@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/showUsers")
    public String showUserstInfo(Model model) {
        List<User> users = userDAO.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("User", new UserDTO()); // Instantiate a new Student object
        return "add-user";
    }

    @PostMapping("/saveUser")
    public String saveUser(User user) {
        userDAO.addUserInf(user);
        return "redirect:/User/showUsers";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable int id, Model model) {
        User user = userDAO.getUserById(id);
        model.addAttribute("User", user);
        return "edit-User";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
        userDAO.updateUserInf(user);
        return "redirect:/User/showUsers";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userDAO.deleteUser(id);
        return "redirect:" + "/User/showUsers";
    }

}
