package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Saida extends EntityId {
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaidaItem> itensVenda;

    @Column(nullable = false)
    private LocalDate dataVenda;

    //usuario que ela esta vinculado



    public Saida() {
    }

    public Saida(List<SaidaItem> itensVenda, LocalDate dataVenda) {
        this.itensVenda = itensVenda;
        this.dataVenda = dataVenda;
    }

    public List<SaidaItem> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<SaidaItem> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
}
