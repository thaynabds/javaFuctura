package br.com.thaynabds.javafuctura.exercicios.poo;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;

public class ContaCorrente extends ContaBancaria {

    private final double taxaManutencao;

    public ContaCorrente(String titular, double saldoInicial, double taxaManutencao) {
        super(titular, saldoInicial);
        if (taxaManutencao < 0) {
            throw new DadoInvalidoException("Taxa de manutenção não pode ser negativa.");
        }
        this.taxaManutencao = taxaManutencao;
    }

    public double getTaxaManutencao() {
        return taxaManutencao;
    }

    @Override
    public void aplicarRegraMensal() {
        if (taxaManutencao == 0) {
            return;
        }
        debitar(taxaManutencao);
    }
}
