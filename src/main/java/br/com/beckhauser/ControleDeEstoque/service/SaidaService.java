package br.com.beckhauser.ControleDeEstoque.service;

import br.com.beckhauser.ControleDeEstoque.enterprise.ValidationException;
import br.com.beckhauser.ControleDeEstoque.model.Produto;
import br.com.beckhauser.ControleDeEstoque.model.Saida;
import br.com.beckhauser.ControleDeEstoque.model.SaidaItem;
import br.com.beckhauser.ControleDeEstoque.repository.ProdutoRepository;
import br.com.beckhauser.ControleDeEstoque.repository.SaidaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaidaService {

    @Autowired
    private SaidaRepository saidaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Saida realizarVenda(Saida saida) throws ValidationException {

        for (SaidaItem item : saida.getItensVenda()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            validarQuantidadePositiva(item.getQuantidadeVendida(), produto.getNome());

            if (produto.getQuantidadeEmEstoque() < item.getQuantidadeVendida()) {
                throw new ValidationException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidadeVendida());
            produtoRepository.save(produto);
        }

        return saidaRepository.save(saida);
    }

    private void validarQuantidadePositiva(int quantidade, String nomeProduto) {
        if (quantidade <= 0) {
            throw new ValidationException("Quantidade vendida deve ser positiva para o produto: " + nomeProduto);
        }
    }

    // GET
    public List<Saida> listarVendas() {
        return saidaRepository.findAll();
    }
}


