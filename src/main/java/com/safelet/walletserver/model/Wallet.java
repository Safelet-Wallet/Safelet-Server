package com.safelet.walletserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "public_key",nullable = false,unique = true)
    private String publicKey;

    @Column(name = "private_key",nullable = false,unique = true)
    private String privateKey;

    @JoinColumn(name = "user", nullable = false)
    private User user;

    @JoinColumn(name = "coin", nullable = false)
    private Coin coin;

    @Column(name = "balance", nullable = false)
    private Double balance;
}
