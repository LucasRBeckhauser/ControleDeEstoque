package br.com.beckhauser.ControleDeEstoque.controller;

import br.com.beckhauser.ControleDeEstoque.model.Entrada;
import br.com.beckhauser.ControleDeEstoque.model.Saida;
import br.com.beckhauser.ControleDeEstoque.service.SaidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/saidas")
public class SaidaController extends AbstractController{
    @Autowired
    private SaidaService service;

    @PostMapping
    public ResponseEntity realizarSaida(@RequestBody Saida entity) {
        Saida save = service.realizarSaida(entity);
        return ResponseEntity.created(URI.create("/saidas" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity acharTodos() {
        List<Saida> saidas = service.listarSaida();
        return ResponseEntity.ok(saidas);
    }

//    @PutMapping("{id}")
//    public ResponseEntity alterarEntrada(@PathVariable("id") Long id, @RequestBody Entrada entity) {
//        Entrada alterado = service.alterarEntrada(id, entity);
//        return ResponseEntity.ok().body(alterado);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity removeEntrada(@PathVariable("id") Long id) {
//        service.excluirEntrada(id);
//        return ResponseEntity.noContent().build();
//    }


}
