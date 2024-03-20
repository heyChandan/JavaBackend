package com.jbdl63.library.Service;

import com.jbdl63.library.Model.Author;
import com.jbdl63.library.Model.Book;
import com.jbdl63.library.Repository.AuthorRepository;
import com.jbdl63.library.Repository.BookRepository;
import com.jbdl63.library.dto.UpdateBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book addNewBook(Book book){
        Book book1 = bookRepository.save(book);
        return book1;
    }

    public Book updateBookByName(UpdateBookDto updateBookDto) {
        Book book = bookRepository.findByBookName(updateBookDto.getBookName());
        book.setBookPrice(updateBookDto.getBookPrice());
        return bookRepository.save(book);
    }

    public void deleteBookByName(String name) {
        Book book = bookRepository.findByBookName(name);
        bookRepository.delete(book);
    }

    public List<Book> fetchAllBook() {
        return bookRepository.findAll();

    }

    public List<Book> getBookByAuthor(String authorName) {
        return bookRepository.findByAuthorAuthorName(authorName);
    }

    public List<Book> fetchBookByCategory(String bookCategory) {
        return bookRepository.findByBookCategory(bookCategory);
    }

}
