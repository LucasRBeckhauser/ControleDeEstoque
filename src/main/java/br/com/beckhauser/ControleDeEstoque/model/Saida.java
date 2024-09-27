package br.com.beckhauser.ControleDeEstoque.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Saida extends EntityId {
    @OneToMany(mappedBy = "saida", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaidaItem> itensVenda;

    @Column(nullable = false)
    private LocalDate dataSaida;

    //usuario que ela esta vinculado



    public Saida() {
    }

    public Saida(List<SaidaItem> itensVenda, LocalDate dataSaida) {
        this.itensVenda = itensVenda;
        this.dataSaida = dataSaida;
    }

    public List<SaidaItem> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<SaidaItem> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }
}
