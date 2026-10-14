package br.com.thaynabds.javafuctura.aula0606;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;

public final class CalculadoraEmprestimo {

    public static final double LIMITE = 1000.0;

    private CalculadoraEmprestimo() {
    }

    public static double valorComJuros(double valor, double taxa) {
        if (valor < 0) {
            throw new DadoInvalidoException("Valor do emprestimo nao pode ser negativo.");
        }
        if (taxa < 0) {
            throw new DadoInvalidoException("Taxa de juros nao pode ser negativa.");
        }
        return valor + (valor * taxa);
    }

    public static boolean aprovado(double valorTotal) {
        return valorTotal <= LIMITE;
    }

    public static String mensagem(double valorTotal) {
        if (aprovado(valorTotal)) {
            return "Emprestimo aprovado!";
        }
        return "Seu emprestimo está além do seu limite";
    }
}
