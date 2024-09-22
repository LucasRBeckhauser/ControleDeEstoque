package br.com.beckhauser.ControleDeEstoque.repository;

import br.com.beckhauser.ControleDeEstoque.model.CompraItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraItemRepository extends JpaRepository <CompraItem, Long> {
}
