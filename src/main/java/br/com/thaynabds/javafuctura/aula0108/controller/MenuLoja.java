package br.com.thaynabds.javafuctura.aula0108.controller;

import br.com.thaynabds.javafuctura.aula0108.model.Produto;
import br.com.thaynabds.javafuctura.aula0108.service.LojaService;
import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;

import java.io.PrintStream;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public final class MenuLoja {

    private final LojaService lojaService;
    private final Scanner entrada;
    private final PrintStream saida;

    public MenuLoja(LojaService lojaService, Scanner entrada, PrintStream saida) {
        this.lojaService = lojaService;
        this.entrada = entrada;
        this.saida = saida;
    }

    public void executar() {
        boolean aberto = true;
        while (aberto && entrada.hasNext()) {
            saida.println("===== Loja Fuctura =====");
            saida.println("1 - Administrador");
            saida.println("2 - Cliente");
            saida.println("3 - Sair");
            saida.println("Digite uma opção");
            Integer opcao = lerInteiro();
            if (opcao == null) {
                continue;
            }
            switch (opcao) {
                case 1 -> menuAdministrador();
                case 2 -> menuCliente();
                case 3 -> {
                    aberto = false;
                    saida.println("Saindo da loja...");
                }
                default -> saida.println("Opção inválida");
            }
        }
    }

    private void menuAdministrador() {
        boolean noMenu = true;
        while (noMenu && entrada.hasNext()) {
            saida.println("==== Admin ====");
            saida.println("1 - Cadastrar");
            saida.println("2 - Listar");
            saida.println("3 - Atualizar");
            saida.println("4 - Remover");
            saida.println("5 - Voltar");
            Integer opcao = lerInteiro();
            if (opcao == null) {
                continue;
            }
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> remover();
                case 5 -> noMenu = false;
                default -> saida.println("Opção inválida");
            }
        }
    }

    private void menuCliente() {
        boolean noMenu = true;
        while (noMenu && entrada.hasNext()) {
            saida.println("==== Cliente ====");
            saida.println("1 - Listar");
            saida.println("2 - Comprar");
            saida.println("3 - Voltar");
            Integer opcao = lerInteiro();
            if (opcao == null) {
                continue;
            }
            switch (opcao) {
                case 1 -> listar();
                case 2 -> comprar();
                case 3 -> noMenu = false;
                default -> saida.println("Opção inválida");
            }
        }
    }

    private void cadastrar() {
        saida.println("Nome do produto:");
        String nome = lerTexto();
        if (nome == null) {
            return;
        }
        saida.println("Valor:");
        Double valor = lerDecimal();
        if (valor == null) {
            return;
        }
        saida.println("Quantidade:");
        Integer quantidade = lerInteiro();
        if (quantidade == null) {
            return;
        }
        try {
            Produto produto = lojaService.cadastrar(nome, valor, quantidade);
            saida.println("Produto cadastrado com id " + produto.getId());
        } catch (DadoInvalidoException ex) {
            saida.println(ex.getMessage());
        }
    }

    private void listar() {
        List<Produto> produtos = lojaService.listar();
        if (produtos.isEmpty()) {
            saida.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto produto : produtos) {
            saida.println(produto.getId()
                    + " - " + produto.getNome()
                    + " | R$ " + formatar(produto.getValor())
                    + " | estoque " + produto.getQuantidade());
        }
    }

    private void atualizar() {
        saida.println("Id do produto:");
        Integer id = lerInteiro();
        if (id == null) {
            return;
        }
        saida.println("Novo nome:");
        String nome = lerTexto();
        if (nome == null) {
            return;
        }
        saida.println("Novo valor:");
        Double valor = lerDecimal();
        if (valor == null) {
            return;
        }
        try {
            Produto produto = lojaService.atualizar(id, nome, valor);
            saida.println("Produto atualizado: " + produto.getNome());
        } catch (DadoInvalidoException ex) {
            saida.println(ex.getMessage());
        }
    }

    private void remover() {
        saida.println("Id do produto:");
        Integer id = lerInteiro();
        if (id == null) {
            return;
        }
        try {
            lojaService.remover(id);
            saida.println("Produto removido.");
        } catch (DadoInvalidoException ex) {
            saida.println(ex.getMessage());
        }
    }

    private void comprar() {
        saida.println("Id do produto:");
        Integer id = lerInteiro();
        if (id == null) {
            return;
        }
        saida.println("Quantidade:");
        Integer quantidade = lerInteiro();
        if (quantidade == null) {
            return;
        }
        try {
            double total = lojaService.comprar(id, quantidade);
            saida.println("Compra realizada. Total: R$ " + formatar(total));
        } catch (DadoInvalidoException ex) {
            saida.println(ex.getMessage());
        }
    }

    private Integer lerInteiro() {
        if (!entrada.hasNextInt()) {
            if (!entrada.hasNext()) {
                return null;
            }
            entrada.next();
            saida.println("Opção inválida");
            return null;
        }
        return entrada.nextInt();
    }

    private Double lerDecimal() {
        if (!entrada.hasNextDouble()) {
            if (!entrada.hasNext()) {
                return null;
            }
            entrada.next();
            saida.println("Valor inválido.");
            return null;
        }
        return entrada.nextDouble();
    }

    private String lerTexto() {
        if (!entrada.hasNext()) {
            return null;
        }
        return entrada.next();
    }

    private String formatar(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }
}
