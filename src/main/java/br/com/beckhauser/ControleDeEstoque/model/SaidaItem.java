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
    @JoinColumn(name = "venda_id", nullable = false)
    private Saida venda;

    @Column(nullable = false,name = "quantidade_vendida")
    private int quantidadeVendida;

    public SaidaItem() {
    }

    public SaidaItem(Produto produto, Saida saida, int quantidadeVendida) {
        this.produto = produto;
        this.venda = saida;
        this.quantidadeVendida = quantidadeVendida;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Saida getVenda() {
        return venda;
    }

    public void setVenda(Saida saida) {
        this.venda = saida;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
}
