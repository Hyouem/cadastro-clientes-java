package br.com.portfolio.clientes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveCriarClienteValido() {
        Cliente cliente = new Cliente(
                1, "Ana", "ANA@EXEMPLO.COM", "(11) 99999-9999"
        );

        assertEquals(1, cliente.getId());
        assertEquals("Ana", cliente.getNome());
        assertEquals("ana@exemplo.com", cliente.getEmail());
    }

    @Test
    void deveRejeitarIdInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente(0, "Ana", "ana@exemplo.com", "11999999999"));
    }

    @Test
    void deveRejeitarNomeVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente(1, " ", "ana@exemplo.com", "11999999999"));
    }

    @Test
    void deveRejeitarEmailInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente(1, "Ana", "email-invalido", "11999999999"));
    }

    @Test
    void deveRejeitarTelefoneInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente(1, "Ana", "ana@exemplo.com", "123"));
    }

    @Test
    void atualizacaoInvalidaNaoDeveAlterarDados() {
        Cliente cliente = new Cliente(
                1, "Ana", "ana@exemplo.com", "11999999999"
        );

        assertThrows(IllegalArgumentException.class,
                () -> cliente.atualizar("Maria", "invalido", "11988888888"));

        assertEquals("Ana", cliente.getNome());
        assertEquals("ana@exemplo.com", cliente.getEmail());
    }
}