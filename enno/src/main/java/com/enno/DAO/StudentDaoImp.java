package com.enno.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.enno.models.Student;
import com.enno.rowmapper.StudentRowMapper;

@Primary
@Repository
public class StudentDaoImp implements StudentDAO {

    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImp(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int updateStudent(Student student) {
        String sql = "UPDATE `student` SET `student_id`=?, `name`=?, `age`=?, `city`=? WHERE `id`=?";
        return jdbcTemplate.update(sql, student.getStudent_id(), student.getName(), student.getAge(), student.getCity(),
                student.getId());
    }

    @Override
    public int deleteStudent(int id) {
        String sql = "DELETE FROM `student` WHERE `id`=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM `student` WHERE `id`=?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new StudentRowMapper());
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM `student`";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    @Override
    public void saveStudent(Student student) {
        String sql = "INSERT INTO `student`(`student_id`, `name`, `age`, `city`) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, student.getStudent_id(), student.getName(), student.getAge(), student.getCity());
        // Use logging instead of System.out.println for production code
        System.out.println("Student saved!");
    }
}
