package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Compra extends EntityId {
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompraItem> itensCompra;

    @Column(nullable = false)
    private LocalDate dataCompra;

    public Compra() {
    }

    public Compra(List<CompraItem> itensCompra, LocalDate dataCompra) {
        this.itensCompra = itensCompra;
        this.dataCompra = dataCompra;
    }

    public List<CompraItem> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(List<CompraItem> itensCompra) {
        this.itensCompra = itensCompra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

}
