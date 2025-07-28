package com.asyahir.statement_query_service.service;

import com.asyahir.statement_query_service.collection.MaybankCredit;
import com.asyahir.statement_query_service.repository.MaybankCreditRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaybankCreditService {

    private final MaybankCreditRepository maybankCreditRepository;

    @Autowired
    public MaybankCreditService(MaybankCreditRepository maybankCreditRepository) {
        this.maybankCreditRepository = maybankCreditRepository;
    }

    public Mono<List<MaybankCredit>> saveMaybankCredit(List<MaybankCredit> maybankCredits) {
        // TODO: logic to categorise
        if (CollectionUtils.isEmpty(maybankCredits)) {
            return Mono.just(List.of());
        }

        List<String> rowHashes = maybankCredits.stream()
                .map(MaybankCredit::getRowHash).collect(Collectors.toList());

        return maybankCreditRepository.findByRowHashIn(rowHashes)
                .collectList()
                .filter(CollectionUtils::isNotEmpty)
                .map(existingCredits -> maybankCredits.stream()
                        .filter(credit -> existingCredits.stream()
                        .noneMatch(c -> StringUtils.equals(credit.getRowHash(), c.getRowHash())))
                        .toList())
                .switchIfEmpty(Mono.just(maybankCredits))
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(cs -> maybankCreditRepository.saveAll(cs).collectList())
                .defaultIfEmpty(List.of());
    }
}
