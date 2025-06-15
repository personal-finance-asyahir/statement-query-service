package com.asyahir.statement_query_service.repository;

import com.asyahir.statement_query_service.collection.MaybankCredit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaybankCreditRepository extends ReactiveMongoRepository<MaybankCredit, Long> {
}
