package br.com.thaynabds.javafuctura.exercicios.poo;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;
import br.com.thaynabds.javafuctura.comum.SaldoInsuficienteException;

public abstract class ContaBancaria {

    private final String titular;
    private double saldo;

    protected ContaBancaria(String titular, double saldoInicial) {
        if (titular == null || titular.isBlank()) {
            throw new DadoInvalidoException("Titular é obrigatório.");
        }
        if (saldoInicial < 0) {
            throw new DadoInvalidoException("Saldo inicial não pode ser negativo.");
        }
        this.titular = titular.trim();
        this.saldo = saldoInicial;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new DadoInvalidoException("Informe um valor maior que zero.");
        }
        saldo += valor;
    }

    protected void debitar(double valor) {
        if (valor <= 0) {
            throw new DadoInvalidoException("Informe um valor maior que zero.");
        }
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para esta operação.");
        }
        saldo -= valor;
    }

    protected void creditar(double valor) {
        if (valor < 0) {
            throw new DadoInvalidoException("Crédito não pode ser negativo.");
        }
        saldo += valor;
    }

    public abstract void aplicarRegraMensal();
}
