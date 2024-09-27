package br.com.beckhauser.ControleDeEstoque.controller;

import br.com.beckhauser.ControleDeEstoque.model.Entrada;
import br.com.beckhauser.ControleDeEstoque.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;

public class EntradaController {

    @Autowired
    private EntradaService service;

    @PostMapping
    public ResponseEntity create(@RequestBody Entrada entity) {
        Entrada save = service.realizarCompra(entity);
        return ResponseEntity.created(URI.create("/entrada" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(){
        List<Entrada> doacao = service.listarCompras();
        return ResponseEntity.ok(doacao);
    }


    @PutMapping("{id}")
    public  ResponseEntity update(@PathVariable("id") Long id, @RequestBody Doacao entity) {
        Doacao alterado = service.alterar(id, entity);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
