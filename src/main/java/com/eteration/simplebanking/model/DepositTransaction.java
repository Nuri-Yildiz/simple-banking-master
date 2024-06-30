package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "deposits")
@NoArgsConstructor
public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public void apply(Account account) {
        account.setBalance(account.getBalance() + this.getAmount());
    }
}
