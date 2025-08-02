package com.asyahir.statement_query_service.controller;

import com.asyahir.statement_query_service.collection.MaybankDebit;
import com.asyahir.statement_query_service.service.MaybankDebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RestController
@RequestMapping("/maybank-debit")
public class MaybankDebitController {

    private final MaybankDebitService maybankDebitService;

    @Autowired
    public MaybankDebitController(MaybankDebitService maybankDebitService) {
        this.maybankDebitService = maybankDebitService;
    }

    @GetMapping("/{userId}")
    public Flux<MaybankDebit> findByUserId(@PathVariable String userId) {
        return maybankDebitService.findAllByUserId(UUID.fromString(userId));
    }


}
