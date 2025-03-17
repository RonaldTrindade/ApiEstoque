package com.example.storecontrol.Service.impl;

import com.example.storecontrol.Model.Perfil;
import com.example.storecontrol.Repository.PerfilRepository;
import com.example.storecontrol.Service.PerfilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class PerfilServiceImpl implements PerfilService {
    private final PerfilRepository perfilRepository;
    @Override
    public Perfil salvarPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }
    @Override
    public Optional<Perfil> buscarPerfilPorId(Long id) {
        return perfilRepository.findById(id);
    }
    @Override
    public List<Perfil> listarPerfis() {
        return perfilRepository.findAll();
    }
    @Override
    public void deletarPerfil(Long id) {
        perfilRepository.deleteById(id);
    }
    @Override
    public List<Perfil> buscarPerfisPorUsuarioId(Long usuarioId) {
        return perfilRepository.findByUsuarioId(usuarioId);
    }
}
