package br.com.thaynabds.javafuctura.exercicios;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;
import br.com.thaynabds.javafuctura.comum.SaldoInsuficienteException;
import br.com.thaynabds.javafuctura.exercicios.arrays.EstatisticaNotas;
import br.com.thaynabds.javafuctura.exercicios.colecoes.AgendaContatos;
import br.com.thaynabds.javafuctura.exercicios.notas.Boletim;
import br.com.thaynabds.javafuctura.exercicios.poo.ContaBancaria;
import br.com.thaynabds.javafuctura.exercicios.poo.ContaCorrente;
import br.com.thaynabds.javafuctura.exercicios.poo.ContaPoupanca;
import br.com.thaynabds.javafuctura.exercicios.repeticao.Tabuada;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExerciciosTest {

    @Test
    void boletimCalculaMediaESituacao() {
        assertEquals(8.0, Boletim.media(7.0, 9.0));
        assertEquals("Aprovado", Boletim.situacao(7.0));
        assertEquals("Recuperação", Boletim.situacao(5.0));
        assertEquals("Reprovado", Boletim.situacao(4.9));
        assertThrows(DadoInvalidoException.class, () -> Boletim.media());
        assertThrows(DadoInvalidoException.class, () -> Boletim.media(11));
        assertThrows(DadoInvalidoException.class, () -> Boletim.situacao(-1));
    }

    @Test
    void tabuadaGeraDezLinhas() {
        List<String> linhas = Tabuada.gerar(7);
        assertEquals(10, linhas.size());
        assertEquals("7 x 1 = 7", linhas.get(0));
        assertEquals("7 x 10 = 70", linhas.get(9));
        assertThrows(DadoInvalidoException.class, () -> Tabuada.gerar(-1));
    }

    @Test
    void estatisticaLeArrayDeNotas() {
        double[] notas = {4.0, 10.0, 7.0};
        assertEquals(7.0, EstatisticaNotas.media(notas));
        assertEquals(10.0, EstatisticaNotas.maior(notas));
        assertEquals(4.0, EstatisticaNotas.menor(notas));
        assertThrows(DadoInvalidoException.class, () -> EstatisticaNotas.media(new double[0]));
    }

    @Test
    void regrasMensaisDeContaCorrenteEPoupanca() {
        ContaBancaria corrente = new ContaCorrente("Ana", 100.0, 10.0);
        ContaBancaria poupanca = new ContaPoupanca("Ana", 100.0, 0.5);
        corrente.aplicarRegraMensal();
        poupanca.aplicarRegraMensal();
        assertEquals(90.0, corrente.getSaldo());
        assertEquals(150.0, poupanca.getSaldo());
        corrente.depositar(5.0);
        assertEquals(95.0, corrente.getSaldo());

        ContaCorrente semTaxa = new ContaCorrente("Ana", 10.0, 0.0);
        semTaxa.aplicarRegraMensal();
        assertEquals(10.0, semTaxa.getSaldo());

        assertThrows(SaldoInsuficienteException.class,
                () -> new ContaCorrente("Ana", 5.0, 10.0).aplicarRegraMensal());
        assertThrows(DadoInvalidoException.class, () -> new ContaCorrente(" ", 10, 1));
        assertThrows(DadoInvalidoException.class, () -> new ContaPoupanca("Ana", -1, 0.01));
        assertThrows(DadoInvalidoException.class, () -> new ContaPoupanca("Ana", 10, -0.1));
        assertEquals(0.5, ((ContaPoupanca) poupanca).getTaxaRendimento());
        assertThrows(DadoInvalidoException.class, () -> new ContaCorrente("Ana", 10, -1));
        assertThrows(DadoInvalidoException.class, () -> poupanca.depositar(0));
    }

    @Test
    void agendaAdicionaBuscaERemove() {
        AgendaContatos agenda = new AgendaContatos();
        agenda.adicionar("Maria", "81999990000");
        agenda.adicionar(" Maria ", "81888880000");
        assertEquals("81888880000", agenda.buscar("Maria").orElseThrow());
        assertEquals(List.of("Maria: 81888880000"), agenda.listar());
        assertTrue(agenda.remover("Maria"));
        assertFalse(agenda.remover("Maria"));
        assertTrue(agenda.buscar("Maria").isEmpty());
        assertThrows(DadoInvalidoException.class, () -> agenda.adicionar("", "1"));
        assertThrows(DadoInvalidoException.class, () -> agenda.adicionar("Joao", " "));
    }
}
