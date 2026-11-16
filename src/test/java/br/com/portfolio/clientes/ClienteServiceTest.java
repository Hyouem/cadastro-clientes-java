package br.com.portfolio.clientes;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private final ClienteService service = new ClienteService();

    @Test
    void deveCadastrarComIdsSequenciais() {
        Cliente primeiro = service.cadastrar("Ana", "ana@exemplo.com", "11999999999");
        Cliente segundo = service.cadastrar("Bia", "bia@exemplo.com", "11888888888");

        assertEquals(1, primeiro.getId());
        assertEquals(2, segundo.getId());
    }

    @Test
    void deveImpedirEmailDuplicado() {
        service.cadastrar("Ana", "ana@exemplo.com", "11999999999");

        assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar("Bia", "ANA@EXEMPLO.COM", "11888888888"));
    }

    @Test
    void deveConsultarCliente() {
        Cliente cliente = service.cadastrar("Ana", "ana@exemplo.com", "11999999999");

        assertSame(cliente, service.consultar(cliente.getId()));
    }

    @Test
    void deveListarClientes() {
        service.cadastrar("Ana", "ana@exemplo.com", "11999999999");
        service.cadastrar("Bia", "bia@exemplo.com", "11888888888");

        assertEquals(2, service.listar().size());
    }

    @Test
    void listaNaoDevePermitirAlteracoesEstruturais() {
        service.cadastrar("Ana", "ana@exemplo.com", "11999999999");

        assertThrows(UnsupportedOperationException.class,
                () -> service.listar().clear());
    }

    @Test
    void deveAtualizarCliente() {
        Cliente cliente = service.cadastrar("Ana", "ana@exemplo.com", "11999999999");

        service.atualizar(cliente.getId(), "Maria", "maria@exemplo.com", "11888888888");

        assertEquals("Maria", service.consultar(cliente.getId()).getNome());
        assertEquals("maria@exemplo.com", service.consultar(cliente.getId()).getEmail());
    }

    @Test
    void deveImpedirEmailDuplicadoNaAtualizacao() {
        service.cadastrar("Ana", "ana@exemplo.com", "11999999999");
        Cliente bia = service.cadastrar("Bia", "bia@exemplo.com", "11888888888");

        assertThrows(IllegalArgumentException.class,
                () -> service.atualizar(
                        bia.getId(), "Bia", "ANA@EXEMPLO.COM", "11888888888"
                ));

        assertEquals("bia@exemplo.com", bia.getEmail());
    }

    @Test
    void deveExcluirCliente() {
        Cliente cliente = service.cadastrar("Ana", "ana@exemplo.com", "11999999999");

        service.excluir(cliente.getId());

        assertTrue(service.listar().isEmpty());
    }

    @Test
    void deveRejeitarConsultaInexistente() {
        assertThrows(NoSuchElementException.class,
                () -> service.consultar(999));
    }

    @Test
    void deveRejeitarExclusaoInexistente() {
        assertThrows(NoSuchElementException.class,
                () -> service.excluir(999));
    }

    @Test
    void cadastroInvalidoNaoDeveConsumirId() {
        assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar(" ", "ana@exemplo.com", "11999999999"));

        Cliente cliente = service.cadastrar(
                "Ana", "ana@exemplo.com", "11999999999"
        );

        assertEquals(1, cliente.getId());
    }
}