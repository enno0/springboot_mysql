package com.enno.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.enno.DAO.StudentDAO;
import com.enno.models.Student;

@Controller
public class StudentController {

    private final StudentDAO studentDAO;

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, Spring Boot!");
        return "hello"; // Template name without file extension
    }

    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping("/showStudent")
    public String showStudentInfo(Model model) {
        List<Student> students = studentDAO.getAllStudents();
        model.addAttribute("students", students);
        return "student-list";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student()); // Instantiate a new Student object
        return "add-student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(Student student) {
        studentDAO.saveStudent(student);
        return "redirect:/showStudent";
    }
}
