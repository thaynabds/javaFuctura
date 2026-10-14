package br.com.thaynabds.javafuctura.aula0108.model;

import br.com.thaynabds.javafuctura.comum.DadoInvalidoException;

public class Produto {

    private final int id;
    private String nome;
    private double valor;
    private int quantidade;

    public Produto(int id, String nome, double valor, int quantidade) {
        if (id <= 0) {
            throw new DadoInvalidoException("Id do produto deve ser positivo.");
        }
        this.id = id;
        setNome(nome);
        setValor(valor);
        definirQuantidade(quantidade);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new DadoInvalidoException("Nome do produto é obrigatório.");
        }
        this.nome = nome.trim();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new DadoInvalidoException("Valor não pode ser negativo.");
        }
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void baixarEstoque(int unidades) {
        if (unidades <= 0) {
            throw new DadoInvalidoException("Quantidade da compra deve ser maior que zero.");
        }
        if (unidades > quantidade) {
            throw new DadoInvalidoException("Estoque insuficiente.");
        }
        quantidade -= unidades;
    }

    private void definirQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new DadoInvalidoException("Quantidade não pode ser negativa.");
        }
        this.quantidade = quantidade;
    }
}
