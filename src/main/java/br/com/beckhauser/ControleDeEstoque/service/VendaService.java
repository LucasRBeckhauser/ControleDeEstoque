package br.com.beckhauser.ControleDeEstoque.service;

import br.com.beckhauser.ControleDeEstoque.enterprise.ValidationException;
import br.com.beckhauser.ControleDeEstoque.model.Produto;
import br.com.beckhauser.ControleDeEstoque.model.Venda;
import br.com.beckhauser.ControleDeEstoque.model.VendaItem;
import br.com.beckhauser.ControleDeEstoque.repository.ProdutoRepository;
import br.com.beckhauser.ControleDeEstoque.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Venda realizarVenda(Venda venda) throws ValidationException {

        for (VendaItem item : venda.getItensVenda()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            validarQuantidadePositiva(item.getQuantidadeVendida(), produto.getNome());

            if (produto.getQuantidadeEmEstoque() < item.getQuantidadeVendida()) {
                throw new ValidationException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidadeVendida());
            produtoRepository.save(produto);
        }

        return vendaRepository.save(venda);
    }

    private void validarQuantidadePositiva(int quantidade, String nomeProduto) {
        if (quantidade <= 0) {
            throw new ValidationException("Quantidade vendida deve ser positiva para o produto: " + nomeProduto);
        }
    }

    // GET
    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }
}


