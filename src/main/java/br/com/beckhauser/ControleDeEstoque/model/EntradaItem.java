package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EntradaItem extends EntityId{

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Entrada compra;

    @Column(nullable = false,name = "quantidade_comprada")
    private int quantidadeComprada;



    public EntradaItem() {
    }

    public EntradaItem(Produto produto, Entrada entrada, int quantidadeComprada) {
        this.produto = produto;
        this.compra = entrada;
        this.quantidadeComprada = quantidadeComprada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Entrada getCompra() {
        return compra;
    }

    public void setCompra(Entrada entrada) {
        this.compra = entrada;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }
}
