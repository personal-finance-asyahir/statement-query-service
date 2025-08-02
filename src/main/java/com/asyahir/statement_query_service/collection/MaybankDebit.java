package com.asyahir.statement_query_service.collection;

import com.asyahir.statement_query_service.pojo.MaybankDebitData;
import com.asyahir.statement_query_service.util.RowHashGeneratorUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Document(collection = "maybank_debit")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
public class MaybankDebit {
    @Id
    @JsonIgnore
    private ObjectId id;
    private UUID userId;
    private Double amount;
    private String description;
    private char operation;
    private LocalDate transactionDate;
    private LocalDateTime insertedDateTime;

    @JsonIgnore
    private String rowHash;

    public MaybankDebit(MaybankDebitData data) {
        this.userId = data.getUserId();
        this.amount = data.getAmount();
        this.description = data.getDescription();
        this.operation = data.getOperation();
        this.transactionDate = data.getTransactionDate();
        this.insertedDateTime = LocalDateTime.now();
        this.rowHash = RowHashGeneratorUtil.generateRowHash(
                transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                String.format("%.2f", amount),
                description,
                userId.toString());
    }
}
