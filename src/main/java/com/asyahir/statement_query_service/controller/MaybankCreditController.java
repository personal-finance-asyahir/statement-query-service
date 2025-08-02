package com.asyahir.statement_query_service.controller;

import com.asyahir.statement_query_service.collection.MaybankCredit;
import com.asyahir.statement_query_service.service.MaybankCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RestController
@RequestMapping("/maybank-credit")
public class MaybankCreditController {

    private final MaybankCreditService maybankCreditService;

    @Autowired
    public MaybankCreditController(MaybankCreditService maybankCreditService) {
        this.maybankCreditService = maybankCreditService;
    }

    @GetMapping("/{userId}")
    public Flux<MaybankCredit> findByUserId(@PathVariable String userId) {
        return maybankCreditService.findAllByUserId(UUID.fromString(userId));
    }
}
