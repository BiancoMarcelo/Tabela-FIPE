package br.com.consultafipe.Tabela.Consulta.FIPE.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// vamos representar com o mesmo nome que os atributos da lista da API então não precisa do @JsonAlias
// classe feita pra nomear e modelar as listas que eu crio

@JsonIgnoreProperties(ignoreUnknown = true) // serve pra eu ignorar o atributo de lista de "anos" na API, la tem duas listas, os códigos e os anos
public record ModeloDeListas(List<Dados> modelos) {
}
