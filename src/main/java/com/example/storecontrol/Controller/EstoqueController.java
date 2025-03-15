package com.example.storecontrol.Controller;


import com.example.storecontrol.Model.Estoque;
import com.example.storecontrol.Service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<Estoque> salvarEstoque(@RequestBody Estoque estoque) {
        Estoque estoqueSalvo = estoqueService.salvarEstoque(estoque);
        return ResponseEntity.ok(estoqueSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarEstoquePorId(@PathVariable Long id) {
        return estoqueService.buscarEstoquePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarEstoques() {
        List<Estoque> estoques = estoqueService.listarEstoques();
        return ResponseEntity.ok(estoques);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        estoqueService.deletarEstoque(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<Estoque>> buscarEstoquesPorPerfilId(@PathVariable Long perfilId) {
        List<Estoque> estoques = estoqueService.buscarEstoquesPorPerfilId(perfilId);
        return ResponseEntity.ok(estoques);
    }
}
