package com.enno.models;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.enno.DAO.StudentDAO;
import com.enno.rowmapper.StudentRowMapper;

public class StudentDaoImp implements StudentDAO {

    @Override
    public int updateStudent(Student student) {
        String sql = "UPDATE `student` SET `student_id`=" + student.getStudent_id()
                + "',`name`=" + student.getName()
                + "',`age`=" + student.getAge()
                + "',`city`=" + student.getCity()
                + "' WHERE 'id'=" + student.getId();
        return jdbcTemplate.update(sql);
    }

    private JdbcTemplate jdbcTemplate;

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM `student` WHERE 'id'=" + id;
        return jdbcTemplate.update(sql);
    }

    public StudentDaoImp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM `student` WHERE `id`=?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new StudentRowMapper());
    }

    @Override
    public List<Student> getAllStudent() {
        String sql = "SELECT * FROM `student` ";
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
        return students;
    }

    @Override
    public void saveStudent(Student student) {
        Object[] studentInfo = {
                student.getStudent_id(),
                student.getName(),
                student.getAge(),
                student.getCity() };
        String sql = "INSERT INTO `student`(`student_id`, `name`, `age`, `city`) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, studentInfo);
        System.out.println("done!");
    }

}
