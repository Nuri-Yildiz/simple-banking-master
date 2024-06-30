package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String owner;
    private double balance;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.apply(this);
        transactions.add(transaction);
    }
}
