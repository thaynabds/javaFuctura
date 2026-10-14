package br.com.thaynabds.javafuctura.aula0108;

import br.com.thaynabds.javafuctura.aula0108.controller.MenuLoja;
import br.com.thaynabds.javafuctura.aula0108.repository.ProdutoMemoriaRepository;
import br.com.thaynabds.javafuctura.aula0108.service.LojaService;

import java.util.Locale;
import java.util.Scanner;

public final class LojaApp {

    private LojaApp() {
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        entrada.useLocale(Locale.US);
        LojaService lojaService = new LojaService(new ProdutoMemoriaRepository());
        new MenuLoja(lojaService, entrada, System.out).executar();
    }
}
