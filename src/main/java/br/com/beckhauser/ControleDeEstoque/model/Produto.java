package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Produto extends EntityId {

    @Column(name = "nome_produto")
    private String nome;

    @Column(name = "descricao_produto",nullable = false)
    private String descricao;

    @Column(name = "preco_produto",nullable = false)
    private Double preco;

    @Column(name= "quantidade_maxima",nullable = false)
    private Integer quantidadeMaxima;

    @Column(name= "quantidade_minima",nullable = false)
    private Integer quantidadeMinima;


    public Produto() {
    }

    public Produto(String nome, String descricao, Double preco, Integer quantidadeMaxima, Integer quantidadeMinima) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeMaxima = quantidadeMaxima;
        this.quantidadeMinima = quantidadeMinima;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(Integer quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }


}
