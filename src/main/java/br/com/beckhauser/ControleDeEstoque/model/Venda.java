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
}
