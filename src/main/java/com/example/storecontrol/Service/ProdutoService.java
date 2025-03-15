package com.example.storecontrol.Service;

import com.example.storecontrol.Model.Produto;
import com.example.storecontrol.Repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }


    public List<Produto> buscarProdutosPorEstoqueId(Long estoqueId) {
        return produtoRepository.findByEstoqueId(estoqueId);
    }

    public Long contarProdutosNoEstoque(Long estoqueId) {
        return produtoRepository.countProdutosByEstoqueId(estoqueId);
    }

    public Integer somarQuantidadeDeProdutosNoEstoque(Long estoqueId) {
        return produtoRepository.somarQuantidadeDeProdutosByEstoqueId(estoqueId);
    }
}
