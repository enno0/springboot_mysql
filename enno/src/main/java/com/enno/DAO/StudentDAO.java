package com.enno.DAO;

import java.util.List;

import com.enno.models.Student;

public interface StudentDAO {
    List<Student> getAllStudent();

    void saveStudent(Student student);

    Student getStudentById(int id);

    int updateStudent(Student student);

    int delete(int id);

}
