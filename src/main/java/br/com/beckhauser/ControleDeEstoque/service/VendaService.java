package br.com.beckhauser.ControleDeEstoque.service;

import br.com.beckhauser.ControleDeEstoque.model.Produto;
import br.com.beckhauser.ControleDeEstoque.model.Venda;
import br.com.beckhauser.ControleDeEstoque.model.VendaItem;
import br.com.beckhauser.ControleDeEstoque.repository.ProdutoRepository;
import br.com.beckhauser.ControleDeEstoque.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Venda realizarVenda(Venda venda) throws Exception {

        // Validação de Venda itens | POST
        for (VendaItem item : venda.getItensVenda()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new Exception("Produto não encontrado: " + item.getProduto().getNome()));

            if (item.getQuantidadeVendida() <= 0) {
                throw new Exception("Quantidade vendida deve ser positiva para o produto: " + produto.getNome());
            }

            if (produto.getQuantidadeEmEstoque() < item.getQuantidadeVendida()) {
                throw new Exception("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // Atualiza o estoque do produto
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidadeVendida());
            produtoRepository.save(produto);
        }

        return vendaRepository.save(venda);
    }
    //GET
    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }
}

