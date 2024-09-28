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
    public Saida realizarSaida(Saida saida) throws ValidationException {

        for (SaidaItem item : saida.getItensVenda()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            validarQuantidadePositiva(item.getQuantidadeSaida(), produto.getNome());

            if (produto.getQuantidadeEmEstoque() < item.getQuantidadeSaida()) {
                throw new ValidationException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidadeSaida());
            produtoRepository.save(produto);
        }

        return saidaRepository.save(saida);
    }

    private void validarQuantidadePositiva(int quantidade, String nomeProduto) {
        if (quantidade <= 0) {
            throw new ValidationException("Quantidade vendida deve ser positiva para o produto: " + nomeProduto);
        }
    }

    @Transactional
    public Saida alterarSaida(Long id, Saida novaSaida) throws ValidationException {
        Saida saidaAtual = saidaRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Saída não encontrada para o ID: " + id));

        saidaAtual.getItensVenda().forEach(item -> {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            // Adiciona a quantidade vendida anteriormente de volta ao estoque
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + item.getQuantidadeSaida());
            produtoRepository.save(produto);
        });

        // Aplica as novas quantidades da nova saída e atualiza outros atributos
        novaSaida.getItensVenda().forEach(item -> {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            validarQuantidadePositiva(item.getQuantidadeSaida(), produto.getNome());

            if (produto.getQuantidadeEmEstoque() < item.getQuantidadeSaida()) {
                throw new ValidationException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // Atualiza o estoque com a nova quantidade
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidadeSaida());

            // Atualiza outros atributos do produto
            produto.setNome(item.getProduto().getNome());
            produto.setDescricao(item.getProduto().getDescricao());
            produto.setValor(item.getProduto().getValor());

            produtoRepository.save(produto);
        });

        return saidaRepository.save(novaSaida);
    }

    // GET
    public List<Saida> listarSaida() {
        return saidaRepository.findAll();
    }

    //DELETE
    @Transactional
    public void excluirSaida(Long id) throws ValidationException {
        Saida saida = saidaRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Saída não encontrada para o ID: " + id));

        // Para cada item de venda, adicionar a quantidade vendida de volta ao estoque
        saida.getItensVenda().forEach(item -> {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            // Adiciona a quantidade vendida de volta ao estoque
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + item.getQuantidadeSaida());
            produtoRepository.save(produto);
        });

        // Exclui a saída após atualizar o estoque
        saidaRepository.deleteById(id);
    }
}


