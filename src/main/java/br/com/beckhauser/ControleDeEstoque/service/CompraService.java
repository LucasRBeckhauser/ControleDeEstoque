package br.com.beckhauser.ControleDeEstoque.service;

import br.com.beckhauser.ControleDeEstoque.enterprise.ValidationException;
import br.com.beckhauser.ControleDeEstoque.model.Compra;
import br.com.beckhauser.ControleDeEstoque.model.CompraItem;
import br.com.beckhauser.ControleDeEstoque.model.Produto;
import br.com.beckhauser.ControleDeEstoque.repository.CompraRepository;
import br.com.beckhauser.ControleDeEstoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Compra realizarCompra(Compra compra) throws ValidationException {

        for (CompraItem item : compra.getItensCompra()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            validarQuantidadePositiva(item.getQuantidadeComprada(), produto.getNome());

            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + item.getQuantidadeComprada());
            produtoRepository.save(produto);
        }

        return compraRepository.save(compra);
    }

    private void validarQuantidadePositiva(int quantidade, String nomeProduto) {
        if (quantidade <= 0) {
            throw new ValidationException("Quantidade comprada deve ser positiva para o produto: " + nomeProduto);
        }
    }

    // GET
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }
}
