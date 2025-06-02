package com.asyahir.statement_query_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaybankCreditData {
    private UUID userId;
    private Double amount;
    private String description;
    private char operation;
    private LocalDate postingDate;
    private LocalDate transactionDate;
}
