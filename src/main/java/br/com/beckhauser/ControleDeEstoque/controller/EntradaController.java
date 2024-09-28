package br.com.beckhauser.ControleDeEstoque.controller;

import br.com.beckhauser.ControleDeEstoque.model.Entrada;
import br.com.beckhauser.ControleDeEstoque.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/entrada")
public class EntradaController {

    @Autowired
    private EntradaService service;

    @PostMapping
    public ResponseEntity realizarCompra(@RequestBody Entrada entity) {
        Entrada save = service.realizarCompra(entity);
        return ResponseEntity.created(URI.create("/entrada" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity acharTodos() {
        List<Entrada> doacao = service.listarCompras();
        return ResponseEntity.ok(doacao);
    }

    @PutMapping("{id}")
    public ResponseEntity alterarEntrada(@PathVariable("id") Long id, @RequestBody Entrada entity) {
        Entrada alterado = service.alterarEntrada(id, entity);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeEntrada(@PathVariable("id") Long id) {
        service.excluirEntrada(id);
        return ResponseEntity.noContent().build();
    }
}

