package JBDL63.JDBCTemplate.controller;

import java.util.List;
import java.util.ArrayList;
import JBDL63.JDBCTemplate.model.Student;
import JBDL63.JDBCTemplate.service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/students")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping
   public ResponseEntity<String> addNewStudent(@Valid @RequestBody Student student){
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

   @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
       if(studentService.deleteById(id) == 1){
           return new ResponseEntity<>(String.format("Student with id: %d, deleted from table successfully",id),HttpStatus.OK);
       }
       return new ResponseEntity<>(String.format("Student not found with id: %d",id), HttpStatus.BAD_REQUEST);
   }

   @PutMapping
    public ResponseEntity<String> updateStudent(@RequestBody Student student){
       if(studentService.updateStudent(student) == 1)
           return new ResponseEntity<>(String.format("Student with id: %d updated successfully",student.getId()),HttpStatus.OK);
       return new ResponseEntity<>(String.format("Student with id: %d not exist",student.getId()),HttpStatus.BAD_REQUEST);
   }

}
