package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Venda extends EntityId {
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VendaItem> itensVenda;

    @Column(nullable = false)
    private LocalDate dataVenda;

    public Venda() {
    }

    public Venda(List<VendaItem> itensVenda, LocalDate dataVenda) {
        this.itensVenda = itensVenda;
        this.dataVenda = dataVenda;
    }

    public List<VendaItem> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<VendaItem> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
}
