package com.jbdl63.library.Controller;

import com.jbdl63.library.Exceptions.BadRequestException;
import com.jbdl63.library.Model.Author;
import com.jbdl63.library.Service.AuthorService;
import com.jbdl63.library.dto.UpdateAuthorDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.aspectj.util.LangUtil.isEmpty;

@RestController
@RequestMapping(value = "/v1/authors", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Validated
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> addNewAuthor(@Valid @RequestBody Author author){
        if(author != null){
            Author author1 = authorService.addNewAuthor(author);
            if(author1 != null)
                return new ResponseEntity<>(author1, HttpStatus.CREATED);
        }
        return null;
    }


    @GetMapping("/{name}")
    @Cacheable(value = "authors", key = "#name")
    public Author fetchAuthorByName(@PathVariable String name){
        if(!isEmpty(name))
            return authorService.fetchAuthorByName(name);
        throw  new BadRequestException("Please Send Author name");
    }

    @GetMapping("/usingParam")
    public Author fetchAuthorByNameUsingParam(@RequestParam String name){
        if(!isEmpty(name))
            return authorService.fetchAuthorByName(name);
        throw  new BadRequestException("Please Send Author name");
    }

    @GetMapping
    public List<Author> fetchAllAvailableAuthors(){
        return authorService.fetchAllAvailableAuthors();
    }

    @PutMapping
    @Validated
    @CachePut
    public Author updateAuthorAddress(@RequestBody @Valid UpdateAuthorDto updateAuthorDto){
        return authorService.updateAuthorAddress(updateAuthorDto);
    }

    @DeleteMapping("/{authorId}")
    @CacheEvict(value = "authors", allEntries = true)
    public void deleteById(@PathVariable Integer authorId){
        authorService.deleteById(authorId);
    }


    @PostMapping("/upload-csv")
    public void uploadAuthorsDataToDatabase(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        String fileContent = new String(multipartFile.getBytes());
        authorService.uploadAuthorsDataToDatabase(fileContent);
    }
}
