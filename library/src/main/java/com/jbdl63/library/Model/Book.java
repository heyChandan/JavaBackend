package com.jbdl63.library.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(unique = true, nullable = false, length = 200)
    private String bookName;

    @Column(nullable = false)
    private String publicationYear;

    @Column(nullable = false, length = 10)
    private String bookEdition;

    @Column(nullable = false)
    private Float bookPrice;

    @Column(nullable = false)
    private String bookCategory;

    @CreationTimestamp
    private LocalDateTime insertionTime;

    @UpdateTimestamp
    private LocalDateTime updationTime;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;


    @ManyToMany(mappedBy = "issuedBook")
    @JsonBackReference
    private List<User> users = new ArrayList() ;

}
