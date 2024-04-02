package com.jbdl.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    @Column(nullable = false)
    private  Long userId;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private Integer dailyLimit;

    @Column(nullable = false)
    private Long dailyTransactionLimit;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

//    2. Wallet:
//
//    - walletId
//    - userId
//    - Balance
//    - dailyLimit
//    - dailyTransactionLimit
//    - CreatedDate
//    - UpdatedDate
}
