package com.example.storecontrol.Service.impl;

import com.example.storecontrol.Model.Produto;
import com.example.storecontrol.Repository.ProdutoRepository;
import com.example.storecontrol.Service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {
    private final ProdutoRepository produtoRepository;
    @Override
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
    @Override
    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }
    @Override
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }
    @Override
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public List<Produto> buscarProdutosPorEstoqueId(Long estoqueId) {
        return produtoRepository.findByEstoqueId(estoqueId);
    }
    @Override
    public Long contarProdutosNoEstoque(Long estoqueId) {
        return produtoRepository.countProdutosByEstoqueId(estoqueId);
    }
    @Override
    public Integer somarQuantidadeDeProdutosNoEstoque(Long estoqueId) {
        return produtoRepository.somarQuantidadeDeProdutosByEstoqueId(estoqueId);
    }
}
