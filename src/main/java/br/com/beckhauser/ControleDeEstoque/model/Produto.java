package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Produto extends EntityId {

    @Column(name = "nome_produto")
    private String nome;

    @Column(name = "descricao_produto",nullable = false)
    private String descricao;

    @Column(name = "valor_produto",nullable = false)
    private Double valor;

    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEmEstoque;

    public Produto() {
    }

    public Produto(String nome, String descricao, Double valor, int quantidadeEmEstoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

}
