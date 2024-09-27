package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SaidaItem extends EntityId{

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "saida_id", nullable = false)
    private Saida saida;

    @Column(nullable = false,name = "quantidade_saida")
    private int quantidadeSaida;

    public SaidaItem() {
    }

    public SaidaItem(Produto produto, Saida saida, int quantidadeSaida) {
        this.produto = produto;
        this.saida = saida;
        this.quantidadeSaida = quantidadeSaida;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Saida getSaida() {
        return saida;
    }

    public void setSaida(Saida saida) {
        this.saida = saida;
    }

    public int getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(int quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }
}
