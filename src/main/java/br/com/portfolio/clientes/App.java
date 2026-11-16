package br.com.portfolio.clientes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {

    private final ClienteService service;
    private final Scanner scanner;

    public App(ClienteService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        new App(new ClienteService(), new Scanner(System.in)).executar();
    }

    public void executar() {
        while (true) {
            System.out.println();
            System.out.println("=== CADASTRO DE CLIENTES ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Consultar");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            if (!scanner.hasNextLine()) {
                return;
            }

            String opcao = scanner.nextLine().trim();

            try {
                switch (opcao) {
                    case "1" -> cadastrar();
                    case "2" -> listar();
                    case "3" -> consultar();
                    case "4" -> atualizar();
                    case "5" -> excluir();
                    case "0" -> {
                        System.out.println("Encerrado.");
                        return;
                    }
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (IllegalArgumentException
                     | NoSuchElementException exception) {
                System.out.println("Erro: " + exception.getMessage());
            }
        }
    }

    private void cadastrar() {
        String nome = ler("Nome: ");
        String email = ler("Email: ");
        String telefone = ler("Telefone: ");

        Cliente cliente = service.cadastrar(nome, email, telefone);
        System.out.println("Cliente cadastrado. ID: " + cliente.getId());
    }

    private void listar() {
        List<Cliente> clientes = service.listar();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        clientes.forEach(this::exibir);
    }

    private void consultar() {
        exibir(service.consultar(lerId()));
    }

    private void atualizar() {
        long id = lerId();
        String nome = ler("Novo nome: ");
        String email = ler("Novo email: ");
        String telefone = ler("Novo telefone: ");

        service.atualizar(id, nome, email, telefone);
        System.out.println("Cliente atualizado.");
    }

    private void excluir() {
        service.excluir(lerId());
        System.out.println("Cliente excluido.");
    }

    private long lerId() {
        String valor = ler("ID: ");

        try {
            long id = Long.parseLong(valor);

            if (id <= 0) {
                throw new NumberFormatException();
            }

            return id;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("ID invalido.");
        }
    }

    private String ler(String mensagem) {
        System.out.print(mensagem);

        if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException("Entrada encerrada.");
        }

        return scanner.nextLine();
    }

    private void exibir(Cliente cliente) {
        System.out.printf(
                "ID: %d | Nome: %s | Email: %s | Telefone: %s%n",
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}