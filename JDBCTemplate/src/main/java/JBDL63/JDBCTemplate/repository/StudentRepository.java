package JBDL63.JDBCTemplate.repository;

import JBDL63.JDBCTemplate.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository { //This Repository class is responsible for communicating with JDBC

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createNewStudent(Student student){
        String insertQuery = "INSERT INTO Student(id,name,department,marks) VALUES(?,?,?,?)";
        return this.jdbcTemplate.update(insertQuery,student.getId(),student.getName(),student.getDepartment(),student.getMarks());
    }
}
