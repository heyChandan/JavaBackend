package JPAImpl1.JPAImpl1.repository;

import JPAImpl1.JPAImpl1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
