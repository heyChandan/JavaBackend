package JPAImpl1.JPAImpl1.repository;

import JPAImpl1.JPAImpl1.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Test
    public void insertData(){
        Book book = Book.builder()
                .bookName("xsa")
                .publicationYear("2022")
                .author("ccc")
                .build();

        bookRepository.save(book);
    }

    @Test
    public void multipleAdd(){
        List<Book> bookList = List.of(
                Book.builder().bookName("xsa1").publicationYear("2023").author("ccc1").build(),
                Book.builder().bookName("xsa2").publicationYear("2024").author("ccc2").build()
        );

        bookRepository.saveAll(bookList);
    }

    @Test
    public void updateData(){
        Book existingBook = bookRepository.findById(1).get();
        existingBook.setBookName("ddd");
        bookRepository.save(existingBook);
    }

    public void deleteData(){
        bookRepository.deleteById(1);
    }
    @Test
    public void fetchData(){
        Book book = bookRepository.findById(1).get();
        System.out.println(book.toString());
        System.out.println("---------------------------------------");

        List<Book> books = bookRepository.findAll();
        books.forEach(bookObj -> System.out.println(bookObj.toString()));
    }

    @Test
    public void fetchByName(){
        Book book = bookRepository.findByBookName("xyz");
        System.out.println(book);
    }
}