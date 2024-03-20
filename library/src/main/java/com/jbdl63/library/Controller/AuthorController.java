package com.jbdl63.library.Controller;

import com.jbdl63.library.Model.Author;
import com.jbdl63.library.Service.AuthorService;
import com.jbdl63.library.dto.UpdateAuthorDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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
    public ResponseEntity<Author> fetchAuthorByName(@PathVariable String name){
        Author fetchedAuthor = authorService.fetchAuthorByName(name);
        if(fetchedAuthor != null)
            return new ResponseEntity<>(fetchedAuthor,HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Author> fetchAllAvailableAuthors(){
        return authorService.fetchAllAvailableAuthors();
    }

    @PutMapping
    @Validated
    public Author updateAuthorAddress(@RequestBody @Valid UpdateAuthorDto updateAuthorDto){
        return authorService.updateAuthorAddress(updateAuthorDto);
    }

    @DeleteMapping("/{authorId}")
    public void deleteById(@PathVariable Integer authorId){
        authorService.deleteById(authorId);
    }


    @PostMapping("/upload-csv")
    public void uploadAuthorsDataToDatabase(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        String fileContent = new String(multipartFile.getBytes());
        authorService.uploadAuthorsDataToDatabase(fileContent);
    }
}
