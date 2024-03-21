package com.jbdl63.library.Service;

import com.jbdl63.library.Exceptions.BadRequestException;
import com.jbdl63.library.Exceptions.DataNotFoundException;
import com.jbdl63.library.Model.Author;
import com.jbdl63.library.Repository.AuthorRepository;
import com.jbdl63.library.dto.UpdateAuthorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author addNewAuthor(Author author) {
           return authorRepository.save(author);
    }

    public Author fetchAuthorByName(String name) {
        return authorRepository.findByAuthorName(name);
    }

    public List<Author> fetchAllAvailableAuthors() {
        return authorRepository.findAll();
    }

    public Author updateAuthorAddress(UpdateAuthorDto updateAuthorDto) {
        try {
            Author author = authorRepository.findById(updateAuthorDto.getAuthorId()).orElseThrow(
                    () -> new DataNotFoundException("Author not exist")
            );
            author.setAddress(updateAuthorDto.getAddress());
            return authorRepository.save(author);
        }catch(RuntimeException e){
            log.error("Error is occurred while working with update operation with exception {}", e.getMessage());
            throw new BadRequestException("Update operation failed due to exception : "+e.getMessage());
        }
    }

    public void deleteById(Integer authorId) {
        Author author = authorRepository.findById(authorId).get();
        authorRepository.delete(author);
    }

    public void uploadAuthorsDataToDatabase(String fileContent) {
        List<String> authorsData = List.of(fileContent.split("\n"));
        List<Author> authors = new ArrayList<>();
        for(int i = 1; i < authorsData.size(); i++) {
            String[] row = authorsData.get(i).split(",");
            authors.add(Author.builder()
                    .authorId(Integer.valueOf(row[0]))
                    .authorName(row[1])
                    .address(row[2])
                    .build());
        }
        authorRepository.saveAll(authors);
    }
}
