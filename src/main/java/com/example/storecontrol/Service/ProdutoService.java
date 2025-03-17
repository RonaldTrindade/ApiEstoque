package com.example.storecontrol.Service;

import com.example.storecontrol.Model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    Produto salvarProduto(Produto produto);
    Optional<Produto> buscarProdutoPorId(Long id);
    List<Produto> listarProdutos();
    void deletarProduto(Long id);
    List<Produto> buscarProdutosPorEstoqueId(Long estoqueId);
    Long contarProdutosNoEstoque(Long estoqueId);
    Integer somarQuantidadeDeProdutosNoEstoque(Long estoqueId);
}
