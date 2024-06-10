package br.com.consultafipe.Tabela.Consulta.FIPE.model;

// fica genérico pq todo caso que tenha codigo e nome vai cair aqui
// tem que ser record, class não aceita os parâmetros
// como os nomes dos parâmetros são identicos as informações contidas na API, não precisa do ALIAS, usa somente em casos que precisamos renomear

public record Dados (String codigo, String nome) {
}
