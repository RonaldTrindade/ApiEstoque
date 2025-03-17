package com.example.storecontrol.Service;

import com.example.storecontrol.Model.Estoque;

import java.util.List;
import java.util.Optional;

public interface EstoqueService {
    Estoque salvarEstoque(Estoque estoque);
    Optional<Estoque> buscarEstoquePorId(Long id);
    List<Estoque> listarEstoques();
    void deletarEstoque(Long id);
    List<Estoque> buscarEstoquesPorPerfilId(Long perfilId) ;
}
