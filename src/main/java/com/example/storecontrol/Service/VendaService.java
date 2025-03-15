package com.example.storecontrol.Service;

import com.example.storecontrol.Model.Venda;
import com.example.storecontrol.Repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    private final VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public Venda salvarVenda(Venda venda) {
        return vendaRepository.save(venda);
    }

    public Optional<Venda> buscarVendaPorId(Long id) {
        return vendaRepository.findById(id);
    }

    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }

    public void deletarVenda(Long id) {
        vendaRepository.deleteById(id);
    }

    public List<Venda> buscarVendasPorProdutoId(Long produtoId) {
        return vendaRepository.findByProdutoId(produtoId);
    }
}
