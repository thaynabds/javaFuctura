package br.com.thaynabds.javafuctura.exercicios.colecoes;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AgendaContatos {

    private final Map<String, String> contatos = new LinkedHashMap<>();

    public void adicionar(String nome, String telefone) {
        String chave = normalizarNome(nome);
        if (telefone == null || telefone.isBlank()) {
            throw new DadoInvalidoException("Telefone é obrigatório.");
        }
        contatos.put(chave, telefone.trim());
    }

    public Optional<String> buscar(String nome) {
        return Optional.ofNullable(contatos.get(normalizarNome(nome)));
    }

    public boolean remover(String nome) {
        return contatos.remove(normalizarNome(nome)) != null;
    }

    public List<String> listar() {
        List<String> linhas = new ArrayList<>();
        for (Map.Entry<String, String> contato : contatos.entrySet()) {
            linhas.add(contato.getKey() + ": " + contato.getValue());
        }
        return linhas;
    }

    private String normalizarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new DadoInvalidoException("Nome do contato é obrigatório.");
        }
        return nome.trim();
    }
}
