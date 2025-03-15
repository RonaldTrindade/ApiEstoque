package com.example.storecontrol.Service;

import com.example.storecontrol.Model.Estoque;
import com.example.storecontrol.Repository.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public Estoque salvarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Optional<Estoque> buscarEstoquePorId(Long id) {
        return estoqueRepository.findById(id);
    }

    public List<Estoque> listarEstoques() {
        return estoqueRepository.findAll();
    }

    public void deletarEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }

    public List<Estoque> buscarEstoquesPorPerfilId(Long perfilId) {
        return estoqueRepository.findByPerfilId(perfilId);
    }
}
