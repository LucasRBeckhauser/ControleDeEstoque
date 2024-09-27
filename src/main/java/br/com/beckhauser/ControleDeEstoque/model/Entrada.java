package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Entrada extends EntityId {
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntradaItem> itensEntrada;

    @Column(nullable = false)
    private LocalDate dataEntrada;


    public Entrada() {
    }

    public Entrada(List<EntradaItem> itensCompra, LocalDate dataEntrada) {
        this.itensEntrada = itensCompra;
        this.dataEntrada = dataEntrada;
    }

    public List<EntradaItem> getItensEntrada() {
        return itensEntrada;
    }

    public void setItensEntrada(List<EntradaItem> itensEntrada) {
        this.itensEntrada = itensEntrada;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

}
