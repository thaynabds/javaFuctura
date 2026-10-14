package br.com.thaynabds.javafuctura.aula1107.model;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;
import br.com.thaynabds.javafuctura.comum.SaldoInsuficienteException;

public class Conta {

    private double saldo;

    public Conta(double saldoInicial) {
        if (saldoInicial < 0) {
            throw new DadoInvalidoException("Saldo inicial nao pode ser negativo.");
        }
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        validarValorPositivo(valor);
        saldo += valor;
    }

    public void sacar(double valor) {
        validarValorPositivo(valor);
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para o saque.");
        }
        saldo -= valor;
    }

    private void validarValorPositivo(double valor) {
        if (valor <= 0) {
            throw new DadoInvalidoException("Informe um valor maior que zero.");
        }
    }
}
