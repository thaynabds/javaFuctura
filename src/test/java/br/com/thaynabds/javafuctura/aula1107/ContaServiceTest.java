package br.com.thaynabds.javafuctura.aula1107;

import br.com.thaynabds.javafuctura.aula1107.controller.MenuBanco;
import br.com.thaynabds.javafuctura.aula1107.model.Conta;
import br.com.thaynabds.javafuctura.aula1107.service.ContaService;
import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;
import br.com.thaynabds.javafuctura.comum.SaldoInsuficienteException;
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

class ContaServiceTest {

    @Test
    void consultaDepositoESaque() {
        ContaService service = new ContaService(new Conta(1500.0));
        assertEquals(1500.0, service.consultar());
        assertEquals(1550.5, service.depositar(50.5));
        assertEquals(1350.5, service.sacar(200.0));
    }

    @Test
    void recusaSaqueMaiorQueOSaldoEValorInvalido() {
        Conta conta = new Conta(100.0);
        assertThrows(SaldoInsuficienteException.class, () -> conta.sacar(150.0));
        assertThrows(DadoInvalidoException.class, () -> conta.sacar(0));
        assertThrows(DadoInvalidoException.class, () -> conta.depositar(-10));
        assertThrows(DadoInvalidoException.class, () -> new Conta(-1));
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    void menuConsultaSaqueDepositoEErros() {
        String saida = executar("1", "2", "200", "3", "50.5", "2", "5000", "2", "0", "3", "0", "2", "abc", "9", "4");
        assertTrue(saida.contains("=====Banco FUC====="));
        assertTrue(saida.contains("Seu saldo atual: R$ 1500.00"));
        assertTrue(saida.contains("Saque realizado com sucesso!"));
        assertTrue(saida.contains("Saldo atual: R$ 1300.00"));
        assertTrue(saida.contains("Valor depositado com sucesso!"));
        assertTrue(saida.contains("Saldo atual: R$ 1350.50"));
        assertTrue(saida.contains("Saldo insuficiente para o saque."));
        assertTrue(saida.contains("Informe um valor maior que zero."));
        assertTrue(saida.contains("Valor inválido."));
        assertTrue(saida.contains("Opção inválida"));
        assertTrue(saida.contains("Saindo do Banco..."));
    }

    @Test
    void mainEncerraNaOpcaoSair() {
        InputStream entradaOriginal = System.in;
        PrintStream saidaOriginal = System.out;
        System.setIn(new ByteArrayInputStream("4\n".getBytes(StandardCharsets.UTF_8)));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            BancoFuc.main(new String[0]);
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
        ContaService service = new ContaService(new Conta(BancoFuc.SALDO_INICIAL));
        new MenuBanco(service, scanner, new PrintStream(buffer, true, StandardCharsets.UTF_8)).executar();
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
