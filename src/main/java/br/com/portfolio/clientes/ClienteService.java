package br.com.portfolio.clientes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ClienteService {

    private final Map<Long, Cliente> clientes = new LinkedHashMap<>();
    private long proximoId = 1;

    public Cliente cadastrar(String nome, String email, String telefone) {
        String emailNormalizado = Cliente.validarEmail(email);
        verificarEmailDisponivel(emailNormalizado, null);

        Cliente cliente = new Cliente(proximoId, nome, emailNormalizado, telefone);

        clientes.put(cliente.getId(), cliente);
        proximoId++;

        return cliente;
    }

    public Cliente consultar(long id) {
        Cliente cliente = clientes.get(id);

        if (cliente == null) {
            throw new NoSuchElementException("Cliente nao encontrado.");
        }

        return cliente;
    }

    public List<Cliente> listar() {
        return List.copyOf(new ArrayList<>(clientes.values()));
    }

    public Cliente atualizar(long id, String nome, String email, String telefone) {
        Cliente cliente = consultar(id);

        String emailNormalizado = Cliente.validarEmail(email);
        verificarEmailDisponivel(emailNormalizado, id);

        cliente.atualizar(nome, emailNormalizado, telefone);
        return cliente;
    }

    public void excluir(long id) {
        if (clientes.remove(id) == null) {
            throw new NoSuchElementException("Cliente nao encontrado.");
        }
    }

    private void verificarEmailDisponivel(String email, Long idIgnorado) {
        for (Cliente cliente : clientes.values()) {
            if (cliente.getEmail().equals(email)
                    && (idIgnorado == null || cliente.getId() != idIgnorado)) {
                throw new IllegalArgumentException("Email ja cadastrado.");
            }
        }
    }
}