package com.example.storecontrol.Controller;

import com.example.storecontrol.Model.Venda;
import com.example.storecontrol.Service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<Venda> salvarVenda(@RequestBody Venda venda) {
        Venda vendaSalva = vendaService.salvarVenda(venda);
        return ResponseEntity.ok(vendaSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVendaPorId(@PathVariable Long id) {
        return vendaService.buscarVendaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listarVendas() {
        List<Venda> vendas = vendaService.listarVendas();
        return ResponseEntity.ok(vendas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Venda>> buscarVendasPorProdutoId(@PathVariable Long produtoId) {
        List<Venda> vendas = vendaService.buscarVendasPorProdutoId(produtoId);
        return ResponseEntity.ok(vendas);
    }
}
