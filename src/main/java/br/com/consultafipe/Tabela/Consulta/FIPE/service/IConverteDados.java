package br.com.consultafipe.Tabela.Consulta.FIPE.service;


import java.util.List;

// Vai converter dados da API para uma classe,
public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);

    //obter lista pq eu não vou devolver um dado qualquer de uma classe, eu quero uma lista daquele dado

    // <T> é a nomeclatura pra algo que não sei o que é ainda, uma varáivel genérica, List<T> eu quero dizer que é uma lista genérica
    // Nessa lista eu vou ter quais dados? json, e uma classe de lista
    <T>List<T> obterLista(String json, Class<T> classe );
}
