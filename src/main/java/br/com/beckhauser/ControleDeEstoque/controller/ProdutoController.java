package br.com.beckhauser.ControleDeEstoque.controller;
import br.com.beckhauser.ControleDeEstoque.model.Produto;
import br.com.beckhauser.ControleDeEstoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity salvarProduto(@RequestBody Produto entity) {
        Produto save = service.salvar(entity);
        return ResponseEntity.created(URI.create("/produtos" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity acharTodos() {
        List<Produto> produtos = service.buscaTodos();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("{id}")
    public ResponseEntity alterarProduto(@PathVariable("id") Long id, @RequestBody Produto entity) {
        Produto alterado = service.alterar(id, entity);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removerProduto(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
