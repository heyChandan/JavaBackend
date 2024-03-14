package JBDL63.JDBCTemplate.service;

import JBDL63.JDBCTemplate.model.Student;
import JBDL63.JDBCTemplate.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository; //Service layer has dependency on Repository layer

    public int createNewStudent(Student student){
        log.info("new student body received:{}", student);
        return studentRepository.createNewStudent(student);
    }

    public List<Student> fetchAllStudent(){
        List<Student> li = studentRepository.fetchAllStudent();

        if(li.isEmpty()) {
            log.info("No student exist");
            throw new RuntimeException("No student exist");
        }
        return li;
    }

    public Student fetchById(int id){
        Student  s = studentRepository.fetchById(id);

        if(s==null){
            log.info("No student exist");
            throw new RuntimeException("No Student exist");
        }
        return s;
    }

    public Integer deleteById(int id){
        log.info("delete request accepted");
        return studentRepository.deleteById(id);
    }

    public int updateStudent(Student student){
        log.info("Update request accepted");
        return studentRepository.updateStudent(student);
    }
}
