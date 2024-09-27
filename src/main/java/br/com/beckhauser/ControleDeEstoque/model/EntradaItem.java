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
    private Entrada entrada;

    @Column(nullable = false,name = "quantidade_entrada")
    private int quantidadeEntrada;

    public EntradaItem() {
    }

    public EntradaItem(Produto produto, Entrada entrada, int quantidadeEntrada) {
        this.produto = produto;
        this.entrada = entrada;
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public int getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(int quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }
}
