package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.dto.TransactionStatus;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{accountNumber}/transactions")
    public ResponseEntity<TransactionStatus> postTransaction(@PathVariable String accountNumber, @RequestBody TransactionDTO transactionDTO) {
        try {
            if ("deposit".equalsIgnoreCase(transactionDTO.getType())) {
                accountService.postTransaction(accountNumber, new DepositTransaction(transactionDTO.getAmount()));
            } else if ("withdrawal".equalsIgnoreCase(transactionDTO.getType())) {
                accountService.postTransaction(accountNumber, new WithdrawalTransaction(transactionDTO.getAmount()));
            }
            return ResponseEntity.ok(new TransactionStatus("OK"));
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.ok(new TransactionStatus("Insufficient Balance"));
        }
    }
}
