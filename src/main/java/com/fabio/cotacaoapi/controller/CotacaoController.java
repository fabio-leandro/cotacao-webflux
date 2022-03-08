package com.fabio.cotacaoapi.controller;

import com.fabio.cotacaoapi.models.Cotacao;
import com.fabio.cotacaoapi.repository.CotacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cotacoes")
public class CotacaoController {

    private final CotacaoRepository cotacaoRepository;

    public CotacaoController(CotacaoRepository cotacaoRepository) {
        this.cotacaoRepository = cotacaoRepository;
    }

    @PostMapping
    public ResponseEntity<Mono<Cotacao>> saveCotacao(@RequestBody Cotacao cotacao){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cotacaoRepository.save(cotacao));
    }

    @GetMapping
    public ResponseEntity<Flux<Cotacao>> getAll(){
        return ResponseEntity.ok(cotacaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Cotacao>> getById(@PathVariable String id){
        return cotacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Cotacao>> updateById(@PathVariable String id, @RequestBody Cotacao cotacao){
        return cotacaoRepository.findById(id)
                .flatMap(c -> {
                    c.setDia(cotacao.getDia());
                    c.setTipoCotacao(cotacao.getTipoCotacao());
                    c.setCompra(cotacao.getCompra());
                    c.setVenda(cotacao.getVenda());
                    return cotacaoRepository.save(c);
                }).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteById(@PathVariable String id){
        return cotacaoRepository.findById(id)
                .flatMap(cotacao -> cotacaoRepository.delete(cotacao)
                        .then(Mono.just(ResponseEntity.noContent().build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }



}
