package com.example.Spring.Security.Demo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    //Authentication
    @Bean
    public UserDetailsService getUserDetailsService(PasswordEncoder passwordEncoder){
        UserDetails admin = User.withUsername("Chandan")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("Jyoti")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    //Encryption

    @Bean
    public PasswordEncoder getPassWordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //Authorization
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf( csrf-> csrf.disable())
//                .authorizeHttpRequests(
//                        auth ->
//                                auth.requestMatchers("/users/allowedToAll").permitAll()
//                                .requestMatchers("/users/allowedToAdmin").hasRole("ADMIN")
//                                .requestMatchers("/users/allowedToUser").hasRole("USER")
//                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
