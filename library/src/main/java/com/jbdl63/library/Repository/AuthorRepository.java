package com.jbdl63.library.Repository;

import com.jbdl63.library.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByAuthorName(String authorName);

}
