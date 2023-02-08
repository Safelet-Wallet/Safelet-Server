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
@Table(name = "transaction", uniqueConstraints = {
    @UniqueConstraint(name = "uq_transaction", columnNames = {"source", "destiny", "date"})
})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "source", nullable = false)
    private User source;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destiny", nullable = false)
    private User destiny;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "coin", nullable = false)
    private Coin coin;
}
