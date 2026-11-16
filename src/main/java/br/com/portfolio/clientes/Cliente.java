package br.com.portfolio.clientes;

import java.util.Locale;
import java.util.regex.Pattern;

public class Cliente {

    private static final Pattern EMAIL = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    private final long id;
    private String nome;
    private String email;
    private String telefone;

    public Cliente(long id, String nome, String email, String telefone) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invalido.");
        }

        this.id = id;
        atualizar(nome, email, telefone);
    }

    public void atualizar(String nome, String email, String telefone) {
        String novoNome = validarNome(nome);
        String novoEmail = validarEmail(email);
        String novoTelefone = validarTelefone(telefone);

        this.nome = novoNome;
        this.email = novoEmail;
        this.telefone = novoTelefone;
    }

    public static String validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatorio.");
        }

        return nome.trim();
    }

    public static String validarEmail(String email) {
        if (email == null || !EMAIL.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("Email invalido.");
        }

        return email.trim().toLowerCase(Locale.ROOT);
    }

    public static String validarTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone obrigatorio.");
        }

        String valor = telefone.trim();
        String digitos = valor.replaceAll("\\D", "");

        if (digitos.length() < 10 || digitos.length() > 11) {
            throw new IllegalArgumentException("Telefone deve ter 10 ou 11 digitos.");
        }

        if (!valor.matches("[+()0-9.\\s-]+")) {
            throw new IllegalArgumentException("Telefone contem caracteres invalidos.");
        }

        return valor;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}