package com.enno.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.enno.DAO.StudentDAO;
import com.enno.dto.StudentDTO;
import com.enno.models.Student;

@Controller
// @RequestMapping("/Student")
public class StudentController {

    @Autowired
    ExceptionContr exceptionContr;

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/UserMain")
    public String getMethodName() {
        return "redirect:" + "/User/showUsers";
    }

    @GetMapping("/UserRoleMain")
    public String getMethodName2() {
        return "redirect:" + "/User_Role/showUserR";
    }

    @GetMapping("/")
    public String Home(Model model) {
        model.addAttribute("message", "Welcome :]");
        return "hello";
    }

    @GetMapping("/login")
    public String Home2(Model model) {
        model.addAttribute("message", "Welcome :]");
        return "logout";
    }

    @GetMapping("/showStudent")
    public String showStudentInfo(Model model) {
        List<Student> students = studentDAO.getAllStudents();
        model.addAttribute("students", students);
        return "student-list";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new StudentDTO()); // Instantiate a new Student object
        return "add-student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(Student student) {
        studentDAO.saveStudent(student);
        return "redirect:/showStudent";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable int id, Model model) {
        Student student = studentDAO.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/update")
    public String updateStudent(Student student) {
        studentDAO.updateStudent(student);
        return "redirect:" + "/showStudent";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentDAO.deleteStudent(id);
        return "redirect:" + "/showStudent";
    }

    @GetMapping("/logout")
    public String logout() {
        // Logic to perform logout actions if needed (invalidate session, clear cookies,
        // etc.)
        // You can add custom logout logic here

        // Redirect to the logout success page
        return "redirect:/logout-success";
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        // Render the logout success page
        return "logout";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

}
