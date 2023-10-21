package com.enno.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.enno.models.Student;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    @Nullable
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getString("age"));
        student.setCity(rs.getString("city"));
        student.setStudent_id(rs.getString("student_id"));
        return student;
    }

}
