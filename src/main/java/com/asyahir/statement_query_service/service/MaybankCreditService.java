package com.asyahir.statement_query_service.service;

import com.asyahir.statement_query_service.collection.MaybankCredit;
import com.asyahir.statement_query_service.repository.MaybankCreditRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaybankCreditService {

    private final MaybankCreditRepository maybankCreditRepository;

    @Autowired
    public MaybankCreditService(MaybankCreditRepository maybankCreditRepository) {
        this.maybankCreditRepository = maybankCreditRepository;
    }

    public void saveMaybankCredit(List<MaybankCredit> maybankCredits) {
        // TODO: logic to categorise
        for (int i=0; i<maybankCredits.size(); i++) {
            MaybankCredit credit = maybankCredits.get(i);
            String row = credit.getTransactionDate() +
                    credit.getDescription() +
                    credit.getAmount() +
                    credit.getUserId() +
                    i;

            String rowHash = DigestUtils.md5Hex(row);
            credit.setRowHash(rowHash);
        }
        maybankCreditRepository.saveAll(maybankCredits).subscribe();
    }
}
