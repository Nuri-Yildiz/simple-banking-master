package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "withdrawals")
@NoArgsConstructor
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public void apply(Account account) throws InsufficientBalanceException {
        double balance = account.getBalance();
        double amount = this.getAmount();
        if (balance >= amount) {
            account.setBalance(balance - amount);
        } else {
            throw new InsufficientBalanceException("Insufficient balance for the withdrawal transaction.");
        }
    }
}
