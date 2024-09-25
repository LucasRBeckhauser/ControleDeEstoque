package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Entrada extends EntityId {
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntradaItem> itensCompra;

    @Column(nullable = false)
    private LocalDate dataCompra;


    public Entrada() {
    }

    public Entrada(List<EntradaItem> itensCompra, LocalDate dataCompra) {
        this.itensCompra = itensCompra;
        this.dataCompra = dataCompra;
    }

    public List<EntradaItem> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(List<EntradaItem> itensCompra) {
        this.itensCompra = itensCompra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

}
