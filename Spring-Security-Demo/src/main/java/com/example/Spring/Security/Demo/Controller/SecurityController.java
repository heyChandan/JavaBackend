package com.example.Spring.Security.Demo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class SecurityController {

    @GetMapping("/allowedToAll")
    public String getString(){
        return "Allowed To All";
    }

    @GetMapping("/allowedToAdmin")
    public String getAdminString(){
        return "Allowed To Admin Only";
    }

    @GetMapping("/allowedToUser")
    public String getUserString(){
        return "Allowed To User Only";
    }

}
