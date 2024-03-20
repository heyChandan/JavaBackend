package com.jbdl63.library.Service;

import com.jbdl63.library.Model.Book;
import com.jbdl63.library.Model.User;
import com.jbdl63.library.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User insertNewUser(User user) {
        log.info("New user accepted");
        return userRepository.save(user);
    }

    public User fetchUserDetails(Integer id) {
        return userRepository.findById(id).get();
    }

    public String deleteUserByName(String userName) {
        String s ="";

        User user = userRepository.findByUserName(userName);
        if(user != null) {
            userRepository.delete(user);
            return s = "User deleted successfully";
        }
        return s = userName+"User not exist";
    }

    public List<Book> fetchIssuedBook(String userName) {
        User user = userRepository.findByUserName(userName);
        return user.getIssuedBook();
    }
}
