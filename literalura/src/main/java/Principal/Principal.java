package Principal;

import Model.DadosLivro;
import Model.Livro;
import Repository.LivroRepository;
import Service.ConsumoApi;
import Service.ConverteDados;

import java.util.*;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";
    private List<DadosLivro> dadosLivros = new ArrayList<>();
    private LivroRepository repositorio;
    private List<Livro> livros = new ArrayList<>();
    private Optional<Livro> livroBusca;


    public Principal(LivroRepository repositorio){
        this.repositorio = repositorio;
    }

    public void exibeMenu(){
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                    Escolha um número de sua opção:
                    1- buscar livro pelo titulo
                    2- listar livros registrados
                    3- listar autores registrados
                    4- listar autores vivos em um determinado ano
                    5- listar livros em um determinado idioma
                    0- sair
                    """;

            System.out.println(menu);
            opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmUmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosEmUmDeterminadoIdioma();
                    break;
                case 0:
                    System.out.println("Saindo");
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void listarLivrosEmUmDeterminadoIdioma() {
        var idioma = """
                insira o idioma para realizar a busca
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """;
        var escolhaIdioma = sc.nextLine();

        livros = repositorio.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutoresVivosEmUmDeterminadoAno() {
        livros = repositorio.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getAno))
                .forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        livros = repositorio.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getAutor))
                .forEach(System.out::println);
    }

    private void listarLivrosRegistrados() {
        livros = repositorio.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private void buscarLivroPeloTitulo() {
        System.out.println("Insira o nome do livro que deseja procurar");
        var nomeLivro = sc.nextLine();
        Optional<Livro> livro = repositorio.findByTituloIgnoreCase(nomeLivro);

        if(livro.isPresent()){
            var livroEncontrada = livro.get();
            List<DadosLivro> livros = new ArrayList<>();
            livros.forEach(System.out::println);

        }else {
            System.out.println("Série não encontrada!");
        }
        livros.forEach(System.out::println);

        System.out.println("-----LIVRO-----");

        DadosLivro dados = getDadosLivro();
        Livro livro = new Livro(dados);
        repositorio.save(livro);
        System.out.println(dados);
    }
}