package com.jbdl63.library.Controller;

import com.jbdl63.library.Model.Book;
import com.jbdl63.library.Model.User;
import com.jbdl63.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> insertNewUser(@RequestBody User user){
        return new ResponseEntity<>(userService.insertNewUser(user), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> fetchUserDetails(@PathVariable Integer id ){
        return new ResponseEntity<>(userService.fetchUserDetails(id), HttpStatus.OK);
    }

    @DeleteMapping("{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName){
        return new ResponseEntity<>(userService.deleteUserByName(userName), HttpStatus.OK);
    }

    @GetMapping("/issued/{userName}")
    public List<Book> fetchIssuedBook(@PathVariable String userName){
        return userService.fetchIssuedBook(userName);
    }


}
