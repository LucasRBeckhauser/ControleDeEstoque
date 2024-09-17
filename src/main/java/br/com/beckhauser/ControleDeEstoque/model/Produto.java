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

}
