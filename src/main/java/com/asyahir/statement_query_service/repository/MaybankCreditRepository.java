package com.asyahir.statement_query_service.repository;

import com.asyahir.statement_query_service.collection.MaybankCredit;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
//import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//@Repository
public interface MaybankCreditRepository extends ReactiveMongoRepository<MaybankCredit, ObjectId> {

    Flux<MaybankCredit> findByRowHashIn(List<String> rowHash);

    Flux<MaybankCredit> findAllByUserId(UUID userId);
}
