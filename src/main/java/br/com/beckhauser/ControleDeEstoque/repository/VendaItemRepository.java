package br.com.beckhauser.ControleDeEstoque.repository;

import br.com.beckhauser.ControleDeEstoque.model.VendaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaItemRepository extends JpaRepository <VendaItem, Long> {
}
