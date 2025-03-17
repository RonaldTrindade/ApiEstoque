package com.example.storecontrol.Service;

import com.example.storecontrol.Model.Venda;

import java.util.List;
import java.util.Optional;

public interface VendaService {
     Venda salvarVenda(Venda venda);
     Optional<Venda> buscarVendaPorId(Long id) ;
     List<Venda> listarVendas();
     void deletarVenda(Long id);
     List<Venda> buscarVendasPorProdutoId(Long produtoId);
}
