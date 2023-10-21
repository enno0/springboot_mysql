package com.enno.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enno.DAO.StudentDAO;
import com.enno.dto.StudentDTO;
import com.enno.models.Student;

@Controller

public class StudentController {
    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/student-list")
    public String showStudentInfo(Model model) {
        List<Student> students = studentDAO.getAllStudent();

        for (Student stu : students) {
            System.out.println(stu);
        }
        model.addAttribute("students", students);
        return "student-list";
    }

    @GetMapping("/add-student")
    public String addStudent(Model model) {
        StudentDTO studentDTO = new StudentDTO();
        model.addAttribute("students", studentDTO);
        return "add-student";
    }

    @PostMapping("/addStudent")
    public String addStudent(Student student) {
        studentDAO.saveStudent(student);
        return "redirect:" + "/showStudent";
    }

    @GetMapping("/edit-student")
    public String editStudent(@RequestParam int id, Model model) {
        Student student = studentDAO.getStudentById(id);
        model.addAttribute("students", student);
        return "edit-student";
    }

    @PostMapping("/update")
    public String updateStudent(Student student) {
        studentDAO.updateStudent(student);
        return "redirect:" + "/showStudent";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentDAO.delete(id);
        return "redirect:" + "/showStudent";
    }
}
