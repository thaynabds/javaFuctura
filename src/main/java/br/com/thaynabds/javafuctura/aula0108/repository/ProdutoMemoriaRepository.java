package br.com.thaynabds.javafuctura.aula0108.repository;

import br.com.thaynabds.javafuctura.aula0108.model.Produto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProdutoMemoriaRepository {

    private final Map<Integer, Produto> produtos = new LinkedHashMap<>();
    private int proximoId = 1;

    public int gerarId() {
        return proximoId++;
    }

    public Produto salvar(Produto produto) {
        produtos.put(produto.getId(), produto);
        return produto;
    }

    public Optional<Produto> buscarPorId(int id) {
        return Optional.ofNullable(produtos.get(id));
    }

    public List<Produto> listar() {
        return new ArrayList<>(produtos.values());
    }

    public boolean remover(int id) {
        return produtos.remove(id) != null;
    }
}
