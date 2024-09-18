package br.com.beckhauser.ControleDeEstoque.repository;

import br.com.beckhauser.ControleDeEstoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long> {
}
