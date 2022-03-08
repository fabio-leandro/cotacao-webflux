package com.fabio.cotacaoapi.models;

import com.fabio.cotacaoapi.models.enums.TipoCotacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cotacoes")
public class Cotacao {

    @Id
    private String id;
    private LocalDate dia;
    private TipoCotacao tipoCotacao;
    private double compra;
    private double venda;
}
