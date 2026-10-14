package br.com.thaynabds.javafuctura.exercicios.repeticao;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public final class Tabuada {

    private Tabuada() {
    }

    public static List<String> gerar(int numero) {
        if (numero < 0) {
            throw new DadoInvalidoException("Número da tabuada não pode ser negativo.");
        }
        List<String> linhas = new ArrayList<>();
        for (int fator = 1; fator <= 10; fator++) {
            linhas.add(numero + " x " + fator + " = " + (numero * fator));
        }
        return linhas;
    }
}
