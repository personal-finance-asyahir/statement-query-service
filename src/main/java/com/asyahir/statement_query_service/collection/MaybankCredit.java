package com.asyahir.statement_query_service.collection;

import com.asyahir.statement_query_service.pojo.MaybankCreditData;
import com.asyahir.statement_query_service.util.RowHashGeneratorUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Document(collection = "maybank_credit")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
public class MaybankCredit {
    @Id
    @JsonIgnore
    private ObjectId id;
    private UUID userId;
    private Double amount;
    private String description;
    private char operation;
    private LocalDate postingDate;
    private LocalDate transactionDate;
    private LocalDateTime insertedDateTime;

    @JsonIgnore
    private String rowHash;

    public MaybankCredit(MaybankCreditData data) {
        this.userId = data.getUserId();
        this.amount = data.getAmount();
        this.description = data.getDescription();
        this.operation = data.getOperation();
        this.postingDate = data.getPostingDate();
        this.transactionDate = data.getTransactionDate();
        this.insertedDateTime = LocalDateTime.now();
        this.rowHash = RowHashGeneratorUtil.generateRowHash(
                transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                String.format("%.2f", amount),
                description,
                userId.toString());
    }

//    public void generateRowHash() {
//
//        String formattedDate = transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        String formattedAmount =  String.format("%.2f", amount);
//
//        String transactions = String.format("%s%s%s%s",
//                formattedDate,
//                description,
//                formattedAmount,
//                userId);
//
//        this.rowHash = DigestUtils.md5Hex(transactions);
//    }
}
