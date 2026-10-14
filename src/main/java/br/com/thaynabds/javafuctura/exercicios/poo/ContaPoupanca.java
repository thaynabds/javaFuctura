package br.com.thaynabds.javafuctura.exercicios.poo;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;

public class ContaPoupanca extends ContaBancaria {

    private final double taxaRendimento;

    public ContaPoupanca(String titular, double saldoInicial, double taxaRendimento) {
        super(titular, saldoInicial);
        if (taxaRendimento < 0) {
            throw new DadoInvalidoException("Taxa de rendimento não pode ser negativa.");
        }
        this.taxaRendimento = taxaRendimento;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    @Override
    public void aplicarRegraMensal() {
        creditar(getSaldo() * taxaRendimento);
    }
}
