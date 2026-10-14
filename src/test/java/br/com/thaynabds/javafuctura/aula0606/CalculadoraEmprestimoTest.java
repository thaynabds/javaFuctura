package br.com.thaynabds.javafuctura.aula0606;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculadoraEmprestimoTest {

    @Test
    void aplicaJurosDeDezPorCento() {
        assertEquals(1100.0, CalculadoraEmprestimo.valorComJuros(1000.0, 0.1));
    }

    @Test
    void aprovaValorDentroDoLimite() {
        assertTrue(CalculadoraEmprestimo.aprovado(1000.0));
        assertEquals("Emprestimo aprovado!", CalculadoraEmprestimo.mensagem(800.0));
    }

    @Test
    void recusaValorAcimaDoLimite() {
        assertFalse(CalculadoraEmprestimo.aprovado(1100.0));
        assertEquals("Seu emprestimo está além do seu limite", CalculadoraEmprestimo.mensagem(1100.0));
    }

    @Test
    void rejeitaValorOuTaxaNegativa() {
        assertThrows(DadoInvalidoException.class, () -> CalculadoraEmprestimo.valorComJuros(-1, 0.1));
        assertThrows(DadoInvalidoException.class, () -> CalculadoraEmprestimo.valorComJuros(100, -0.1));
    }

    @Test
    void mainImprimeOsCalculosDaAula() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
        try {
            VariaveisApp.main(new String[0]);
        } finally {
            System.setOut(original);
        }
        String texto = buffer.toString(StandardCharsets.UTF_8);
        assertTrue(texto.contains("2437.17"));
        assertTrue(texto.contains("Antonio Henrique"));
        assertTrue(texto.contains("O resultado foi: 50"));
        assertTrue(texto.contains("1100.0"));
        assertTrue(texto.contains("além do seu limite"));
    }
}
