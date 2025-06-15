package com.asyahir.statement_query_service.controller;

import com.asyahir.statement_query_service.collection.MaybankCredit;
import com.asyahir.statement_query_service.pojo.MaybankCreditData;
import com.asyahir.statement_query_service.service.MaybankCreditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatementProcessEventListener {

    private final ObjectMapper objectMapper;

    private final MaybankCreditService maybankCreditService;

    @Autowired
    public StatementProcessEventListener(ObjectMapper objectMapper, MaybankCreditService maybankCreditService) {
        this.objectMapper = objectMapper;
        this.maybankCreditService = maybankCreditService;
    }

    @KafkaListener(topics = "statement.process.maybankcredit")
    public void statementProcessMaybankCredit(String message,
                                              @Header(name= KafkaHeaders.RECEIVED_KEY, required = false) String key,
                                              @Header(name = KafkaHeaders.CORRELATION_ID, required = false) String corrId)
            throws JsonProcessingException {

        List<MaybankCreditData> maybankCreditDataList = objectMapper.readValue(message, new TypeReference<>() {});

        List<MaybankCredit> maybankCreditList = maybankCreditDataList.stream()
                .map(MaybankCredit::new)
                .toList();

        maybankCreditService.saveMaybankCredit(maybankCreditList);
    }
}
