package com.asyahir.statement_query_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaybankDebitData {
    private UUID userId;
    private Double amount;
    private Double statementBalance;
    private String description;
    private char operation;
    private LocalDate transactionDate;
}
