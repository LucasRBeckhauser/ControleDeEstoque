package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Entrada {

    @Column (name = "data_entrada",nullable = false)
    private LocalDate dataEntrada;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "quantidade_entrada", nullable = false)
    private Integer quantidadeEntrada;

    @OneToMany(mappedBy = "produto")
    private List<Entrada> entradas;

    public Entrada() {
    }

    public Entrada(List<Entrada> entradas, Integer quantidadeEntrada, Produto produto, LocalDate dataEntrada) {
        this.entradas = entradas;
        this.quantidadeEntrada = quantidadeEntrada;
        this.produto = produto;
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(Integer quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }
}
