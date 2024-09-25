package br.com.beckhauser.ControleDeEstoque.service;

import br.com.beckhauser.ControleDeEstoque.enterprise.ValidationException;
import br.com.beckhauser.ControleDeEstoque.model.Produto;
import br.com.beckhauser.ControleDeEstoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(Produto entity) {
        if (entity.getNome() == null || entity.getNome().isEmpty()) {
            throw new ValidationException("O nome do produto não pode estar vazio.");
        }
        if (entity.getValor() < 0) {
            throw new ValidationException("O valor do produto não pode ser negativo.");
        }
        return repository.save(entity);
    }

    public List<Produto> buscaTodos() {
        return repository.findAll();
    }

    public Produto buscaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Produto não encontrado para o ID: " + id));
    }

    public Produto alterar(Long id, Produto alterado) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ValidationException("Produto não encontrado para o ID: " + id));

        if (alterado.getNome() == null || alterado.getNome().isEmpty()) {
            throw new ValidationException("O nome do produto não pode estar vazio.");
        }

        produto.setNome(alterado.getNome());
        produto.setDescricao(alterado.getDescricao());
        produto.setValor(alterado.getValor());
        produto.setQuantidadeEmEstoque(alterado.getQuantidadeEmEstoque());

        return repository.save(produto);
    }

    public void remover(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ValidationException("Produto não encontrado para o ID: " + id));
        repository.deleteById(id);

    }
}

