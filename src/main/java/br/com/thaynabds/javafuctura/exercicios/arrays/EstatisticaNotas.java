package br.com.thaynabds.javafuctura.exercicios.arrays;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;

public final class EstatisticaNotas {

    private EstatisticaNotas() {
    }

    public static double media(double[] notas) {
        validar(notas);
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.length;
    }

    public static double maior(double[] notas) {
        validar(notas);
        double maior = notas[0];
        for (int indice = 1; indice < notas.length; indice++) {
            if (notas[indice] > maior) {
                maior = notas[indice];
            }
        }
        return maior;
    }

    public static double menor(double[] notas) {
        validar(notas);
        double menor = notas[0];
        for (int indice = 1; indice < notas.length; indice++) {
            if (notas[indice] < menor) {
                menor = notas[indice];
            }
        }
        return menor;
    }

    private static void validar(double[] notas) {
        if (notas == null || notas.length == 0) {
            throw new DadoInvalidoException("Informe ao menos uma nota.");
        }
    }
}
