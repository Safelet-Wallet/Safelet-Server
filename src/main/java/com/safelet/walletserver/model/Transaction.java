package com.safelet.walletserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "source", nullable = false)
    private User source;

    @JoinColumn(name = "destiny", nullable = false)
    private User destiny;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    //Todo How to do unique groups

    @Column(name = "amount", nullable = false)
    private Double amount;

    @JoinColumn(name = "coin",nullable = false)
    private Coin coin;
}
