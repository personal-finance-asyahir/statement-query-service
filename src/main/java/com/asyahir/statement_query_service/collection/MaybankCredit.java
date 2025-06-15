package com.asyahir.statement_query_service.collection;

import com.asyahir.statement_query_service.pojo.MaybankCreditData;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collation = "maybank_credit")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaybankCredit {
    @Id
    private ObjectId id;
    private UUID userId;
    private Double amount;
    private String description;
    private char operation;
    private LocalDate postingDate;
    private LocalDate transactionDate;
    private LocalDateTime insertedDateTime;
    private String rowHash;

    public MaybankCredit(MaybankCreditData data) {
        this.userId = data.getUserId();
        this.amount = data.getAmount();
        this.description = data.getDescription();
        this.operation = data.getOperation();
        this.postingDate = data.getPostingDate();
        this.transactionDate = data.getTransactionDate();
        this.insertedDateTime = LocalDateTime.now();
    }
}
