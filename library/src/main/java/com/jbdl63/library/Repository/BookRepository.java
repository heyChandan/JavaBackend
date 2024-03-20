package com.jbdl63.library.Repository;

import com.jbdl63.library.Model.Author;
import com.jbdl63.library.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    public Book findByBookName(String name);

    public List<Book> findByBookCategory(String bookCategory);


    public List<Book> findByAuthorAuthorName(String authorName);
}
