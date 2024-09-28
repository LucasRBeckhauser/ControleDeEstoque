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

//    Utilizar o service é o correto. Centralizar no service
//    private ProdutoService produtoService;
//
//    public EntradaService(ProdutoService produtoService) {
//        this.produtoService = produtoService;
//    }

    //dessa forma tu tem só o obj de compra e n dos itens, o certo é passar
    //por parametro uma lista de compra itens List<CompraItens>
    //que ai tu tem de fato o obj de itens
    //pq se n dessa forma ele vai tentar percorrer uma lista null


    //POST
    @Transactional
    public Entrada entradaProduto(Entrada entrada) throws ValidationException {

        entrada.getItensEntrada().forEach(item ->  {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            validarQuantidadePositiva(item.getQuantidadeEntrada(), produto.getNome());

            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + item.getQuantidadeEntrada());
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

        entradaAtual.getItensEntrada().forEach(item -> {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            // Subtrai a quantidade comprada anteriormente do estoque
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidadeEntrada());
            produtoRepository.save(produto);
        });

        // Aplica as novas quantidades da nova entrada
        novaEntrada.getItensEntrada().forEach(item -> {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            // Valida e atualiza o estoque com a nova quantidade
            validarQuantidadePositiva(item.getQuantidadeEntrada(), produto.getNome());
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + item.getQuantidadeEntrada());

            // Atualiza outros atributos do produto
            produto.setNome(item.getProduto().getNome());
            produto.setDescricao(item.getProduto().getDescricao());
            produto.setValor(item.getProduto().getValor());

            produtoRepository.save(produto);

        });

        return entradaRepository.save(novaEntrada);
    }


    // GET
    public List<Entrada> listarProdutos() {
        return entradaRepository.findAll();
    }

    //DELETE
    @Transactional
    public void excluirEntrada(Long id) throws ValidationException {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Entrada não encontrada para o ID: " + id));

        // Para cada item de compra, subtrair a quantidade comprada do estoque
        entrada.getItensEntrada().forEach(item -> {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ValidationException("Produto não encontrado: " + item.getProduto().getNome()));

            if (produto.getQuantidadeEmEstoque() < item.getQuantidadeEntrada()) {
                throw new ValidationException("Erro ao excluir a entrada: o estoque não pode ficar negativo para o produto: " + produto.getNome());
            }

            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidadeEntrada());
            produtoRepository.save(produto);
        });

        // Exclui a entrada após atualizar o estoque
        entradaRepository.deleteById(id);
    }
}


