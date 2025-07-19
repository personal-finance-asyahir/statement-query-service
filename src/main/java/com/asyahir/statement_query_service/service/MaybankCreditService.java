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

    public void saveMaybankCredit(List<MaybankCredit> maybankCredits) {
        // TODO: logic to categorise
        if (CollectionUtils.isEmpty(maybankCredits)) return;

        List<String> rowHashes = maybankCredits.stream()
                .map(MaybankCredit::getRowHash).collect(Collectors.toList());

        maybankCreditRepository.findByRowHashIn(rowHashes)
                .collectList()
                .filter(CollectionUtils::isNotEmpty)
                .map(existingCredits -> maybankCredits.stream()
                        .filter(credit -> existingCredits.stream()
                        .noneMatch(c -> StringUtils.equals(credit.getRowHash(), c.getRowHash())))
                        .collect(Collectors.toUnmodifiableList()))
                .switchIfEmpty(Mono.just(maybankCredits))
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(cs -> maybankCreditRepository.saveAll(cs).collectList())
                .subscribe();

    }
}
