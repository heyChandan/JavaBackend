package JPAImpl1.JPAImpl1.repository;

import JPAImpl1.JPAImpl1.model.Address;
import JPAImpl1.JPAImpl1.model.Course;
import JPAImpl1.JPAImpl1.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Test
  public void saveAll(){
      Student std = Student.builder()
              .Id(1)
              .name("Pranav")
              .Phn(900000000)
              .build();

      Address address = Address.builder()
              .pin(754212)
              .city("Kendrapara")
              .student(std)
              .build();

      std.setAddress(address);
      studentRepository.save(std);
  }

  @Test
  public void dataWithCourse(){
        Student stud = Student.builder().Id(1).name("Pranav").build();

        List<Course> courseList = List.of(
                Course.builder().courseName("Java").teacher("Pranav").build(),
                Course.builder().courseName("Sql").teacher("Mukul").build()
        );

        stud.setCourses(courseList);
        studentRepository.save(stud);
    }
}