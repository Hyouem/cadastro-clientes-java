package br.com.portfolio.clientes;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void deveExecutarFluxoCompletoDoMenu() {
        String entrada = String.join("\n",
                "1", "Ana", "ana@exemplo.com", "11999999999",
                "2",
                "3", "1",
                "4", "1", "Maria", "maria@exemplo.com", "11888888888",
                "5", "1",
                "2",
                "0"
        ) + "\n";

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream original = System.out;

        try (PrintStream saida = new PrintStream(
                buffer, true, StandardCharsets.UTF_8);
             Scanner scanner = new Scanner(entrada)) {

            System.setOut(saida);

            new App(new ClienteService(), scanner).executar();
        } finally {
            System.setOut(original);
        }

        String resultado = buffer.toString(StandardCharsets.UTF_8);

        assertTrue(resultado.contains("Cliente cadastrado. ID: 1"));
        assertTrue(resultado.contains("Cliente atualizado."));
        assertTrue(resultado.contains("Cliente excluido."));
        assertTrue(resultado.contains("Nenhum cliente cadastrado."));
        assertTrue(resultado.contains("Encerrado."));
    }
}