package com.jbdl63.library;

import com.jbdl63.library.Exceptions.BadRequestException;
import com.jbdl63.library.Exceptions.DataNotFoundException;
import com.jbdl63.library.Model.Author;
import com.jbdl63.library.Repository.AuthorRepository;
import com.jbdl63.library.Service.AuthorService;
import com.jbdl63.library.dto.UpdateAuthorDto;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    AuthorService authorService;

    @Mock
    AuthorRepository authorRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewAuthor(){
        Author author = Author.builder().authorId(50).authorName("Babu").address("BBSR").build();

        when(authorRepository.save(any())).thenReturn(author);
        Author a = authorService.addNewAuthor(author);
        Assertions.assertThat(a).isEqualTo(author);
    }

    @Test
    public void testUploadAuthorsDataToDatabase(){
        String fileContent = "authorId,authorName,address\n" +
                "4, Jaydev, Bangalore\n" +
                "5, Pranav, Mashyapradesh\n" +
                "6, Sishant, Odisha\n" +
                "7, Debesh, United State of America\n" +
                "8, Rahul, Odisha";

        authorService.uploadAuthorsDataToDatabase(fileContent);
        verify(authorRepository, times(1)).saveAll(any());
    }

    @Test
    public void testUpdateAuthor(){
        when(authorRepository.findById(1)).thenReturn(null);
        Exception exception = assertThrows(BadRequestException.class, ()-> authorService.updateAuthorAddress(UpdateAuthorDto.builder().address("abc").authorId(1).build()));
        assertTrue(exception.getMessage().contains("Update operation failed due to exception : "));
    }
    @Test
    public void testUpdateAuthorAddress(){
        Author author = Author.builder().authorId(1).address("Bbsr").build();
        UpdateAuthorDto updateAuthorDto = UpdateAuthorDto.builder().authorId(1).address("CTC").build();

        when(authorRepository.findById(any())).thenReturn(Optional.ofNullable(author));
        when(authorRepository.save(author)).thenReturn(author);
        Author e = authorService.updateAuthorAddress(updateAuthorDto);
        e.setAddress(updateAuthorDto.getAddress());
        Assertions.assertThat(e).isEqualTo(author);
    }

//    @Test
//     public void testUpdateAuthorDataNotFound(){
//        UpdateAuthorDto updateAuthorDto = UpdateAuthorDto.builder().authorId(1).address("Shimla").build();
//        when(authorRepository.findById(2)).thenThrow(new DataNotFoundException("Author not exist"));
//        Exception exception = assertThrows(DataNotFoundException.class, ()-> authorService.updateAuthorAddress(updateAuthorDto));
//        assertTrue(exception.getMessage().contains("Author not exist"));
//     }

    @Test
    public void testFetchAuthorByName(){
        Author author = Author.builder().authorId(1).authorName("Chandan").build();
        when(authorRepository.findByAuthorName(author.getAuthorName())).thenReturn(author);
        Author a = authorService.fetchAuthorByName(author.getAuthorName());
        Assertions.assertThat(a).isEqualTo(author);
    }

    @Test
    public void testFetchAllAvailableAuthors(){
        List<Author> authorList = List.of(Author.builder().authorId(1).authorName("Raja").build(),Author.builder().authorId(2).authorName("Ram").build());
        when(authorRepository.findAll()).thenReturn(authorList);
        List<Author> aL = authorService.fetchAllAvailableAuthors();
        Assertions.assertThat(aL).isEqualTo(authorList);
    }

    @Test
    public void testDeleteById(){
        Author author = Author.builder().authorId(1).build();
        when(authorRepository.findById(author.getAuthorId())).thenReturn(Optional.of(author));
        authorService.deleteById(1);
        verify(authorRepository,times(1)).delete(author);
    }

}
