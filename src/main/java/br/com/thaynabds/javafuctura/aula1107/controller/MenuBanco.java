package br.com.thaynabds.javafuctura.aula1107.controller;

import br.com.thaynabds.javafuctura.aula1107.service.ContaService;
import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;
import br.com.thaynabds.javafuctura.comum.SaldoInsuficienteException;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public final class MenuBanco {

    private final ContaService contaService;
    private final Scanner entrada;
    private final PrintStream saida;

    public MenuBanco(ContaService contaService, Scanner entrada, PrintStream saida) {
        this.contaService = contaService;
        this.entrada = entrada;
        this.saida = saida;
    }

    public void executar() {
        saida.println("=====Banco FUC=====");
        boolean aberto = true;
        while (aberto) {
            imprimirOpcoes();
            if (!entrada.hasNextInt()) {
                if (!entrada.hasNext()) {
                    break;
                }
                entrada.next();
                saida.println("Opção inválida");
                continue;
            }
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1 -> consultar();
                case 2 -> sacar();
                case 3 -> depositar();
                case 4 -> {
                    aberto = false;
                    saida.println("Saindo do Banco...");
                }
                default -> saida.println("Opção inválida");
            }
        }
    }

    private void imprimirOpcoes() {
        saida.println("====Opções====");
        saida.println("1 - Consulta");
        saida.println("2 - Saque");
        saida.println("3 - Deposito");
        saida.println("4 - Sair");
        saida.println("Digite uma opção");
    }

    private void consultar() {
        saida.println("Seu saldo atual: R$ " + formatar(contaService.consultar()));
    }

    private void sacar() {
        saida.println("Digite o valor que você quer retirar:");
        Double valor = lerValor();
        if (valor == null) {
            return;
        }
        try {
            double saldo = contaService.sacar(valor);
            saida.println("Saque realizado com sucesso!");
            saida.println("Saldo atual: R$ " + formatar(saldo));
        } catch (SaldoInsuficienteException | DadoInvalidoException ex) {
            saida.println(ex.getMessage());
        }
    }

    private void depositar() {
        saida.println("Digite o valor que você deseja depositar:");
        Double valor = lerValor();
        if (valor == null) {
            return;
        }
        try {
            double saldo = contaService.depositar(valor);
            saida.println("Valor depositado com sucesso!");
            saida.println("Saldo atual: R$ " + formatar(saldo));
        } catch (DadoInvalidoException ex) {
            saida.println(ex.getMessage());
        }
    }

    private Double lerValor() {
        if (!entrada.hasNextDouble()) {
            if (entrada.hasNext()) {
                entrada.next();
            }
            saida.println("Valor inválido.");
            return null;
        }
        return entrada.nextDouble();
    }

    private String formatar(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }
}
