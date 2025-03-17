package com.example.storecontrol.Service.impl;

import com.example.storecontrol.Model.Estoque;
import com.example.storecontrol.Repository.EstoqueRepository;
import com.example.storecontrol.Service.EstoqueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class EstoqueServiceImpl implements EstoqueService {
    private final EstoqueRepository estoqueRepository;
    @Override
    public Estoque salvarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }
    @Override
    public Optional<Estoque> buscarEstoquePorId(Long id) {return estoqueRepository.findById(id);}
    @Override
    public List<Estoque> listarEstoques() {
        return estoqueRepository.findAll();
    }
    @Override
    public void deletarEstoque(Long id) {estoqueRepository.deleteById(id);}
    @Override
    public List<Estoque> buscarEstoquesPorPerfilId(Long perfilId) {
        return estoqueRepository.findByPerfilId(perfilId);
    }
}
