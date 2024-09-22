package br.com.beckhauser.ControleDeEstoque.repository;

import br.com.beckhauser.ControleDeEstoque.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository <Compra, Long> {
}
