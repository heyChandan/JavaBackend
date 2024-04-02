package com.jbdl.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
    private Long dailyLimit;

    @Column(nullable = false)
    private Integer dailyTransactionLimit;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public Wallet(Long userId){
        this.userId = userId;
        this.balance = 0.0;
        this.dailyLimit = 10000L;
        this.dailyTransactionLimit = 10;

    }

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
