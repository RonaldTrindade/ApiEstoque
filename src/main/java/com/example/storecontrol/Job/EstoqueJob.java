package com.example.storecontrol.Job;

import com.example.storecontrol.Model.Estoque;
import com.example.storecontrol.Model.Produto;
import com.example.storecontrol.Model.Usuario;
import com.example.storecontrol.Repository.EstoqueRepository;
import com.example.storecontrol.Service.impl.EmailService;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class EstoqueJob {

    private final EstoqueRepository estoqueRepository;
    private final EmailService emailService;

    public EstoqueJob(EstoqueRepository estoqueRepository, EmailService emailService) {
        this.estoqueRepository = estoqueRepository;
        this.emailService = emailService;
    }
    @Scheduled(fixedRate = 3600000) // 3600000 ms = 1 hora
    public void verificarEstoque() {
        List<Estoque> estoques = estoqueRepository.findAll();

        for (Estoque estoque : estoques) {
            int quantidadeAtual = estoque.getProdutos().stream()
                    .mapToInt(Produto::getQuantidade)
                    .sum();
            int capacidadeTotal = estoque.getCapacidadeTotal();

            double percentual = (double) quantidadeAtual / capacidadeTotal * 100;

            if (percentual <= 30) {
                Usuario usuario = estoque.getPerfil().getUsuario();
                String assunto = "Alerta de Estoque Baixo";
                String mensagem = String.format(
                        "O estoque '%s' está com %.2f%% da capacidade. Verifique urgentemente!",
                        estoque.getNome(), percentual
                );

                emailService.enviarEmail(usuario.getEmail(), assunto, mensagem);
            }
        }
    }
}
