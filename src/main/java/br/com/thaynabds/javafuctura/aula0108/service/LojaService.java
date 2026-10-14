package br.com.thaynabds.javafuctura.aula0108.service;

import br.com.thaynabds.javafuctura.aula0108.model.Produto;
import br.com.thaynabds.javafuctura.aula0108.repository.ProdutoMemoriaRepository;
import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;

import java.util.List;

public class LojaService {

    private final ProdutoMemoriaRepository repository;

    public LojaService(ProdutoMemoriaRepository repository) {
        this.repository = repository;
    }

    public Produto cadastrar(String nome, double valor, int quantidade) {
        Produto produto = new Produto(repository.gerarId(), nome, valor, quantidade);
        return repository.salvar(produto);
    }

    public List<Produto> listar() {
        return repository.listar();
    }

    public Produto buscar(int id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new DadoInvalidoException("Produto não encontrado."));
    }

    public Produto atualizar(int id, String nome, double valor) {
        Produto produto = buscar(id);
        produto.setNome(nome);
        produto.setValor(valor);
        return produto;
    }

    public void remover(int id) {
        if (!repository.remover(id)) {
            throw new DadoInvalidoException("Produto não encontrado.");
        }
    }

    public double comprar(int id, int quantidade) {
        Produto produto = buscar(id);
        produto.baixarEstoque(quantidade);
        return produto.getValor() * quantidade;
    }
}
