package br.com.thaynabds.javafuctura.aula0108;

import br.com.thaynabds.javafuctura.aula0108.controller.MenuLoja;
import br.com.thaynabds.javafuctura.aula0108.model.Produto;
import br.com.thaynabds.javafuctura.aula0108.repository.ProdutoMemoriaRepository;
import br.com.thaynabds.javafuctura.aula0108.service.LojaService;
import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LojaServiceTest {

    @Test
    void cadastraAtualizaCompraERemove() {
        LojaService loja = new LojaService(new ProdutoMemoriaRepository());
        Produto produto = loja.cadastrar("Caderno", 12.5, 10);

        assertEquals(1, produto.getId());
        assertEquals(1, loja.listar().size());

        loja.atualizar(1, "Caderno universitario", 15.0);
        assertEquals("Caderno universitario", loja.buscar(1).getNome());
        assertEquals(30.0, loja.comprar(1, 2));
        assertEquals(8, loja.buscar(1).getQuantidade());

        loja.remover(1);
        assertTrue(loja.listar().isEmpty());
        assertThrows(DadoInvalidoException.class, () -> loja.buscar(1));
        assertThrows(DadoInvalidoException.class, () -> loja.remover(1));
    }

    @Test
    void rejeitaDadosInvalidosEEstoqueInsuficiente() {
        LojaService loja = new LojaService(new ProdutoMemoriaRepository());
        loja.cadastrar("Caneta", 2.0, 1);
        assertThrows(DadoInvalidoException.class, () -> loja.cadastrar(" ", 2.0, 1));
        assertThrows(DadoInvalidoException.class, () -> loja.cadastrar("Lapis", -1, 1));
        assertThrows(DadoInvalidoException.class, () -> loja.cadastrar("Lapis", 1, -1));
        assertThrows(DadoInvalidoException.class, () -> loja.comprar(1, 5));
        assertThrows(DadoInvalidoException.class, () -> new Produto(0, "X", 1, 1));
    }

    @Test
    void menuCadastraListaCompraESai() {
        String saida = executar(
                "1",
                "1", "Caderno", "12.5", "10",
                "2",
                "9",
                "5",
                "2",
                "1",
                "2", "1", "2",
                "2", "1", "99",
                "3",
                "abc",
                "3"
        );
        assertTrue(saida.contains("Produto cadastrado com id 1"));
        assertTrue(saida.contains("1 - Caderno | R$ 12.50 | estoque 10"));
        assertTrue(saida.contains("Opção inválida"));
        assertTrue(saida.contains("Compra realizada. Total: R$ 25.00"));
        assertTrue(saida.contains("Estoque insuficiente."));
        assertTrue(saida.contains("Saindo da loja..."));
    }

    @Test
    void menuAtualizaRemoveEListaVazio() {
        String saida = executar(
                "1",
                "2",
                "3", "9", "Nome", "10",
                "4", "9",
                "1", "Borracha", "3.0", "4",
                "3", "1", "BorrachaBranca", "4.5",
                "4", "1",
                "5",
                "2",
                "1",
                "3",
                "3"
        );
        assertTrue(saida.contains("Nenhum produto cadastrado."));
        assertTrue(saida.contains("Produto não encontrado."));
        assertTrue(saida.contains("Produto atualizado: BorrachaBranca"));
        assertTrue(saida.contains("Produto removido."));
    }

    @Test
    void mainEncerraNaOpcaoSair() {
        InputStream entradaOriginal = System.in;
        PrintStream saidaOriginal = System.out;
        System.setIn(new ByteArrayInputStream("3\n".getBytes(StandardCharsets.UTF_8)));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            LojaApp.main(new String[0]);
        } finally {
            System.setIn(entradaOriginal);
            System.setOut(saidaOriginal);
        }
    }

    private String executar(String... linhas) {
        String entrada = String.join("\n", linhas) + "\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(entrada.getBytes(StandardCharsets.UTF_8)));
        scanner.useLocale(Locale.US);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        LojaService loja = new LojaService(new ProdutoMemoriaRepository());
        new MenuLoja(loja, scanner, new PrintStream(buffer, true, StandardCharsets.UTF_8)).executar();
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
