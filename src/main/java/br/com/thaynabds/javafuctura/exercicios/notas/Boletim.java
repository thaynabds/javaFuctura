package br.com.thaynabds.javafuctura.exercicios.notas;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;

public final class Boletim {

    private Boletim() {
    }

    public static double media(double... notas) {
        if (notas == null || notas.length == 0) {
            throw new DadoInvalidoException("Informe ao menos uma nota.");
        }
        double soma = 0;
        for (double nota : notas) {
            if (nota < 0 || nota > 10) {
                throw new DadoInvalidoException("Nota fora do intervalo de 0 a 10.");
            }
            soma += nota;
        }
        return soma / notas.length;
    }

    public static String situacao(double media) {
        if (media < 0 || media > 10) {
            throw new DadoInvalidoException("Média fora do intervalo de 0 a 10.");
        }
        if (media >= 7.0) {
            return "Aprovado";
        }
        if (media >= 5.0) {
            return "Recuperação";
        }
        return "Reprovado";
    }
}
