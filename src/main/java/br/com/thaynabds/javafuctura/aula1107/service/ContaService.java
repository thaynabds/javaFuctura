package br.com.thaynabds.javafuctura.aula1107.service;

import br.com.thaynabds.javafuctura.aula1107.model.Conta;

public class ContaService {

    private final Conta conta;

    public ContaService(Conta conta) {
        this.conta = conta;
    }

    public double consultar() {
        return conta.getSaldo();
    }

    public double sacar(double valor) {
        conta.sacar(valor);
        return conta.getSaldo();
    }

    public double depositar(double valor) {
        conta.depositar(valor);
        return conta.getSaldo();
    }
}
