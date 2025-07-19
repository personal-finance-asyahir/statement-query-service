package com.asyahir.statement_query_service.repository;

import com.asyahir.statement_query_service.collection.MaybankCredit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
//import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface MaybankCreditRepository extends ReactiveMongoRepository<MaybankCredit, Long> {

    Flux<MaybankCredit> findByRowHashIn(List<String> rowHash);
}
