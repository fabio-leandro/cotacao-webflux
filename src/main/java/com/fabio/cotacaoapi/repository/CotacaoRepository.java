package com.fabio.cotacaoapi.repository;

import com.fabio.cotacaoapi.models.Cotacao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotacaoRepository extends ReactiveMongoRepository<Cotacao,String> {

}
