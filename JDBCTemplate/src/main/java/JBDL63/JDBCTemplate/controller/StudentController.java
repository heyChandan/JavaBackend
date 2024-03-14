package JBDL63.JDBCTemplate.controller;

import java.util.List;
import java.util.ArrayList;
import JBDL63.JDBCTemplate.model.Student;
import JBDL63.JDBCTemplate.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

   @GetMapping
    public ResponseEntity<List<Student>> fetchAllStudent(){
         return new ResponseEntity<>(studentService.fetchAllStudent(), HttpStatus.OK);
   }

   @GetMapping("{id}")
   public ResponseEntity<Student> fetchById(@PathVariable int id){
        return new ResponseEntity<>(studentService.fetchById(id), HttpStatus.OK);
   }

}
