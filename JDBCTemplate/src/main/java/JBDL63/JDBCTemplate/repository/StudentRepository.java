package JBDL63.JDBCTemplate.repository;

import JBDL63.JDBCTemplate.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
@Repository
public class StudentRepository { //This Repository class is responsible for communicating with JDBC

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createNewStudent(Student student){
        String insertQuery = "INSERT INTO Student(id,name,department,marks) VALUES(?,?,?,?)";
        return this.jdbcTemplate.update(insertQuery,student.getId(),student.getName(),student.getDepartment(),student.getMarks());
    }

    public List<Student> fetchAllStudent(){
        String selectQueryForFetchAll = "SELECT * from Student";
        return this.jdbcTemplate.query(selectQueryForFetchAll,(resultSet,rowNo)->
                Student.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .department(resultSet.getString("department"))
                .marks(resultSet.getDouble("marks"))
                .build());
    }

    public Student fetchById(Integer id){
        String selectQueryForFetchAll = "SELECT * from Student WHERE id = ?";
        return this.jdbcTemplate.queryForObject(selectQueryForFetchAll,(resultSet,rowNo)->
                Student.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .department(resultSet.getString("department"))
                .marks(resultSet.getDouble("marks"))
                .build(), id);
    }

}
