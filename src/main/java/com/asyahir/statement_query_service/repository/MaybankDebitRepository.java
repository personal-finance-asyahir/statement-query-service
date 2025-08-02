package com.asyahir.statement_query_service.repository;

import com.asyahir.statement_query_service.collection.MaybankCredit;
import com.asyahir.statement_query_service.collection.MaybankDebit;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface MaybankDebitRepository extends ReactiveMongoRepository<MaybankDebit, ObjectId> {
    Flux<MaybankDebit> findByRowHashIn(List<String> rowHash);
}
