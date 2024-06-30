package com.eteration.simplebanking.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private String type;
    private double amount;
}
