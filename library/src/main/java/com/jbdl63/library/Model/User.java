package com.jbdl63.library.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    private Integer userId;

    private String userName;

    private Integer userMobileNo;

    private String userEmailId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Book_Issued",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> issuedBook = new ArrayList<>();
}
