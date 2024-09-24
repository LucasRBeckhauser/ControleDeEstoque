package br.com.beckhauser.ControleDeEstoque.service;

import br.com.beckhauser.ControleDeEstoque.model.Compra;
import br.com.beckhauser.ControleDeEstoque.model.CompraItem;
import br.com.beckhauser.ControleDeEstoque.model.Produto;
import br.com.beckhauser.ControleDeEstoque.repository.CompraRepository;
import br.com.beckhauser.ControleDeEstoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;

    public CompraService(CompraRepository compraRepository, ProdutoRepository produtoRepository) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Compra realizarCompra(Compra compra) throws Exception {

        // Validação de Compra itens | POST
        for (CompraItem item : compra.getItensCompra()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new Exception("Produto não encontrado: " + item.getProduto().getNome()));

            if (item.getQuantidadeComprada() <= 0) {
                throw new Exception("Quantidade comprada deve ser positiva para o produto: " + produto.getNome());
            }

            // Atualiza o estoque do produto
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + item.getQuantidadeComprada());
            produtoRepository.save(produto);
        }

        return compraRepository.save(compra);
    }

    // GET
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }


}
