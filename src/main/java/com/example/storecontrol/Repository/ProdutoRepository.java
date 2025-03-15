package com.example.storecontrol.Repository;

import com.example.storecontrol.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByEstoqueId(Long estoqueId);

    @Query("SELECT COUNT(p) FROM Produto p WHERE p.estoque.id = :estoqueId")
    Long countProdutosByEstoqueId(@Param("estoqueId") Long estoqueId);

    @Query("SELECT SUM(p.quantidade) FROM Produto p WHERE p.estoque.id = :estoqueId")
    Integer somarQuantidadeDeProdutosByEstoqueId(@Param("estoqueId") Long estoqueId);
}
