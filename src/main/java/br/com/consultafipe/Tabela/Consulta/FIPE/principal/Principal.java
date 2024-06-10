package br.com.consultafipe.Tabela.Consulta.FIPE.principal;

import br.com.consultafipe.Tabela.Consulta.FIPE.model.Dados;
import br.com.consultafipe.Tabela.Consulta.FIPE.model.ModeloDeListas;
import br.com.consultafipe.Tabela.Consulta.FIPE.model.Veiculos;
import br.com.consultafipe.Tabela.Consulta.FIPE.service.ConsumoApi;
import br.com.consultafipe.Tabela.Consulta.FIPE.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversorDeDados = new ConverteDados();

    //private final é uma constante
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        var menu = """
                ***OPÇÕES ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar: 
                """;

        var menuCodigo = """
                Escolha um código de veículo para prosseguir: 
                """;

        System.out.println(menu);
        var opcaoEscolhidaMenu = leitura.nextLine();
        String endereco;

        if (opcaoEscolhidaMenu.toLowerCase().contains("car")) {
            endereco =  URL_BASE + "carros/marcas";
        } else if (opcaoEscolhidaMenu.toLowerCase().contains("mo")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        //fazer esse consumo, essa entrada do usuário receber uma resposta ---- consumoApi vai fazer essa requisição
        // endereco recebe a entrada do usuário e joga pra obter dados -> obter dados joga pro conversor de dados
        var json = consumo.obterDados(endereco);

        var marcas = conversorDeDados.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println(menuCodigo);
        var opcaoEscolhidaCodigo = leitura.nextLine();

        // sobrescrevendo o endereço e o json, para isso, eu não uso o var na frente, eu uso a variavel diretamente

        endereco = endereco + "/" + opcaoEscolhidaCodigo + "/modelos";
        json = consumo.obterDados(endereco);
        // pra representar uma lista de modelos de carros
        // obterDados pq nos modelamos essa variavel coma uma lista la no ModeloDeListas
        var listaDeModelos = conversorDeDados.obterDados(json, ModeloDeListas.class);
        System.out.println("\nModelos dessa marca: ");

        listaDeModelos.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite o nome do carro que deseja buscar: ");
        var nomeVeiculo = leitura.nextLine();

        List<Dados> modelosFiltrados = listaDeModelos.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                        .collect(Collectors.toList());

        System.out.println("\n Modelos filtrados ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("\n Digite o codigo do modelo: ");
        var codigoModelo = leitura.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumo.obterDados(endereco);
        List<Dados> anosDeModeloDoCarroDisponivel = conversorDeDados.obterLista(json, Dados.class);
        List<Veiculos> veiculo = new ArrayList<>();

        for (int i = 0; i < anosDeModeloDoCarroDisponivel.size(); i++) {
            var enderecoAnos = endereco + "/" + anosDeModeloDoCarroDisponivel.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            Veiculos veiculos = conversorDeDados.obterDados(json, Veiculos.class);
            veiculo.add(veiculos);
            
        }
        System.out.println("\n Veiculos filtrados com avaliações por ano\n");
        veiculo.forEach(System.out::println);

    }
}
