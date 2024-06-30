package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Transactional
    public void postTransaction(String accountNumber, Transaction transaction) throws InsufficientBalanceException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.post(transaction);
        accountRepository.save(account);
    }
}
