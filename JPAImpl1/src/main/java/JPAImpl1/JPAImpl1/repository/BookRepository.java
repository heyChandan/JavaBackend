package JPAImpl1.JPAImpl1.repository;

import JPAImpl1.JPAImpl1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
 public Book findByBookName(String name);
}
