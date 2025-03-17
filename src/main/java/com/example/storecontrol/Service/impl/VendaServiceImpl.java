package com.example.storecontrol.Service.impl;

import com.example.storecontrol.Model.Venda;
import com.example.storecontrol.Repository.VendaRepository;
import com.example.storecontrol.Service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;
    @Override
    public Venda salvarVenda(Venda venda) {return vendaRepository.save(venda); }
    @Override
    public Optional<Venda> buscarVendaPorId(Long id) {
        return vendaRepository.findById(id);
    }
    @Override
    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }
    @Override
    public void deletarVenda(Long id) {
        vendaRepository.deleteById(id);
    }
    @Override
    public List<Venda> buscarVendasPorProdutoId(Long produtoId) {
        return vendaRepository.findByProdutoId(produtoId);
    }

    private void  movimenetarDinheiro (){}
}
