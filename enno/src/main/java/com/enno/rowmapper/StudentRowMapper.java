package com.enno.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.enno.models.Student;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

        Student student = new Student();

        student.setId(rs.getInt("id"));
        student.setStudent_id(rs.getInt("student_id"));
        student.setName(rs.getString("name"));
        student.setCity(rs.getString("city"));
        student.setAge(rs.getInt("age"));

        return student;
    }
}
