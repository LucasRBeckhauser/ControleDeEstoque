package br.com.beckhauser.ControleDeEstoque.service;

import br.com.beckhauser.ControleDeEstoque.enterprise.ValidationException;
import br.com.beckhauser.ControleDeEstoque.model.Entrada;
import br.com.beckhauser.ControleDeEstoque.model.Produto;
import br.com.beckhauser.ControleDeEstoque.repository.EntradaRepository;
import br.com.beckhauser.ControleDeEstoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    //dessa forma tu tem só o obj de compra e n dos itens, o certo é passar
    //por parametro uma lista de compra itens List<CompraItens>
    //que ai tu tem de fato o obj de itens
    //pq se n dessa forma ele vai tentar percorrer uma lista null

    @Transactional
    public Entrada realizarCompra(Entrada entrada) throws ValidationException {

        entrada.getItensCompra().forEach(item ->  {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            validarQuantidadePositiva(item.getQuantidadeComprada(), produto.getNome());

            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + item.getQuantidadeComprada());
            produtoRepository.save(produto);
        });

        return entradaRepository.save(entrada);
    }

    private void validarQuantidadePositiva(int quantidade, String nomeProduto) {
        if (quantidade <= 0) {
            throw new ValidationException("Quantidade comprada deve ser positiva para o produto: " + nomeProduto);
        }
    }


    // Put
    @Transactional
    public Entrada alterarEntrada(Long id, Entrada novaEntrada) throws ValidationException {
        Entrada entradaAtual = entradaRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Entrada não encontrada para o ID: " + id));

        entradaAtual.getItensCompra().forEach(item -> {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            // Subtrai a quantidade comprada anteriormente do estoque
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidadeComprada());
            produtoRepository.save(produto);
        });

        // Aplica as novas quantidades da nova entrada
        novaEntrada.getItensCompra().forEach(item -> {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            // Valida e atualiza o estoque com a nova quantidade
            validarQuantidadePositiva(item.getQuantidadeComprada(), produto.getNome());
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + item.getQuantidadeComprada());

            // Atualiza outros atributos do produto
            produto.setNome(item.getProduto().getNome());
            produto.setDescricao(item.getProduto().getDescricao());
            produto.setValor(item.getProduto().getValor());

            produtoRepository.save(produto);

        });

        return entradaRepository.save(novaEntrada);
    }


    // GET
    public List<Entrada> listarCompras() {
        return entradaRepository.findAll();
    }
}
