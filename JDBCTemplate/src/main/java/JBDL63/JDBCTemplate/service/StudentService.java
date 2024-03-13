package JBDL63.JDBCTemplate.service;

import JBDL63.JDBCTemplate.model.Student;
import JBDL63.JDBCTemplate.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository; //Service layer has dependency on Repository layer

    public int createNewStudent(Student student){
        log.info("new student body received:{}", student);
        return studentRepository.createNewStudent(student);
    }
}
