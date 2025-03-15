package com.example.storecontrol.Controller;

import com.example.storecontrol.Model.Produto;
import com.example.storecontrol.Service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.salvarProduto(produto);
        return ResponseEntity.ok(produtoSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estoque/{estoqueId}")
    public ResponseEntity<List<Produto>> buscarProdutosPorEstoqueId(@PathVariable Long estoqueId) {
        List<Produto> produtos = produtoService.buscarProdutosPorEstoqueId(estoqueId);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/estoque/{estoqueId}/quantidade")
    public ResponseEntity<Long> contarProdutosNoEstoque(@PathVariable Long estoqueId) {
        Long quantidade = produtoService.contarProdutosNoEstoque(estoqueId);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estoque/{estoqueId}/soma-quantidade")
    public ResponseEntity<Integer> somarQuantidadeDeProdutosNoEstoque(@PathVariable Long estoqueId) {
        Integer soma = produtoService.somarQuantidadeDeProdutosNoEstoque(estoqueId);
        return ResponseEntity.ok(soma);
    }
}