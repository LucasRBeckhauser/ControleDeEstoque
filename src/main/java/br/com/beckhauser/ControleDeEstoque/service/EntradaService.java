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

    // GET
    public List<Entrada> listarCompras() {
        return entradaRepository.findAll();
    }
}
