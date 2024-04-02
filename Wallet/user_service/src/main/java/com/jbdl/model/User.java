package com.jbdl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is blank for current user")
    @Column(nullable = false)
    private String userFullName;

    @NotBlank(message = "Mobile Number is blank for current user")
    @Column(unique = true, nullable = false)
    private  String userMobileNo;

    @NotBlank(message = "Email Id is blank for current user")
    @Column(unique = true, nullable = false)
    private String userEmailId;


    private String userAddress;

    @Column(unique = true)
    private String userPan;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;
    /*
    *     - userId
    - userFullName - Not Null, Unique
    - userMobileNo - Not Null, Unique, Validated
    - userEmailId - Not Null, Unique, Validated
    - userAddress
    - userPan
    - CreatedDate
    - UpdatedDate
    * */
}
