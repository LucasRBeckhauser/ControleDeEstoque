package br.com.beckhauser.ControleDeEstoque.repository;

import br.com.beckhauser.ControleDeEstoque.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository <Venda, Long> {
}
