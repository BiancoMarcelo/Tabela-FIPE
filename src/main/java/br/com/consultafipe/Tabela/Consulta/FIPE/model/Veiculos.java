package br.com.consultafipe.Tabela.Consulta.FIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//ignorar o que não esta mapeado, tem coisas que eu não usei da lista da API
@JsonIgnoreProperties(ignoreUnknown = true)

public record Veiculos(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") Integer ano,
        @JsonAlias("Combustivel") String combustivel) {
}
