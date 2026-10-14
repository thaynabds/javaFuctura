package br.com.thaynabds.javafuctura.aula1107;

import br.com.thaynabds.javafuctura.aula1107.controller.MenuBanco;
import br.com.thaynabds.javafuctura.aula1107.model.Conta;
import br.com.thaynabds.javafuctura.aula1107.service.ContaService;

import java.util.Locale;
import java.util.Scanner;

public final class BancoFuc {

    public static final double SALDO_INICIAL = 1500.0;

    private BancoFuc() {
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        entrada.useLocale(Locale.US);
        ContaService contaService = new ContaService(new Conta(SALDO_INICIAL));
        new MenuBanco(contaService, entrada, System.out).executar();
    }
}
