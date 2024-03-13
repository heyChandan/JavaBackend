package JBDL63.JDBCTemplate.controller;

import JBDL63.JDBCTemplate.model.Student;
import JBDL63.JDBCTemplate.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping
   public ResponseEntity<String> addNewStudent(@RequestBody Student student){
        if(studentService.createNewStudent(student) == 1)
            return new ResponseEntity<>(String.format("new student with name the Name: %s is added"
                    , student.getName()), HttpStatus.OK);
        return new ResponseEntity<>("Couldn't inserted", HttpStatus.BAD_REQUEST);
   }
}
