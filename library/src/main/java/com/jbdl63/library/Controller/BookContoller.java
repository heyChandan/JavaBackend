package com.jbdl63.library.Controller;

import com.jbdl63.library.Model.Book;
import com.jbdl63.library.Repository.BookRepository;
import com.jbdl63.library.Service.BookService;
import com.jbdl63.library.dto.UpdateBookDto;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookContoller {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addNewBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addNewBook(book), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody UpdateBookDto updateBookDto){
        return new ResponseEntity<>(bookService.updateBookByName(updateBookDto), HttpStatus.OK) ;
    }

    @DeleteMapping("/{name}")
    public void deleteBook(@PathVariable String name){
        bookService.deleteBookByName(name);
    }

    @GetMapping
    public ResponseEntity<List<Book>> fetchAllBook(){
        return new ResponseEntity<>(bookService.fetchAllBook(), HttpStatus.OK);
    }

    @GetMapping("/auth/{authorName}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable("authorName") String authorName){
        return new ResponseEntity<>(bookService.getBookByAuthor(authorName), HttpStatus.OK);
    }

    @GetMapping("/{bookCategory}")
    public ResponseEntity<List<Book>> fetchAllBook(@PathVariable String bookCategory){
        return new ResponseEntity<>(bookService.fetchBookByCategory(bookCategory), HttpStatus.OK);
}
//		- Insert New Book
//		- Update existing Book details
//		- Delete existing book
//		- Fetch Book
//		- Fetch Books by Author
//		- Fetch Books by Category
}
