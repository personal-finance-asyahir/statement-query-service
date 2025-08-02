package com.asyahir.statement_query_service.service;

import com.asyahir.statement_query_service.collection.MaybankCredit;
import com.asyahir.statement_query_service.collection.MaybankDebit;
import com.asyahir.statement_query_service.repository.MaybankDebitRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MaybankDebitService {

    private final MaybankDebitRepository maybankDebitRepository;

    @Autowired
    public MaybankDebitService(MaybankDebitRepository maybankDebitRepository) {
        this.maybankDebitRepository = maybankDebitRepository;
    }

    public Mono<List<MaybankDebit>> saveMaybankDebit(List<MaybankDebit> maybankDebits) {
        if (CollectionUtils.isEmpty(maybankDebits)) {
            return Mono.just(List.of());
        }

        List<String> rowHashes = maybankDebits.stream()
                .map(MaybankDebit::getRowHash).collect(Collectors.toList());

        return maybankDebitRepository.findByRowHashIn(rowHashes)
                .collectList()
                .filter(CollectionUtils::isNotEmpty)
                .map(existingCredits -> maybankDebits.stream()
                        .filter(credit -> existingCredits.stream()
                                .noneMatch(c -> StringUtils.equals(credit.getRowHash(), c.getRowHash())))
                        .toList())
                .switchIfEmpty(Mono.just(maybankDebits))
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(cs -> maybankDebitRepository.saveAll(cs).collectList())
                .defaultIfEmpty(List.of());
    }

    public Flux<MaybankDebit> findAllByUserId(UUID userId) {
        return maybankDebitRepository.findAllByUserId(userId);
    }

}
