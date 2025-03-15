package com.example.storecontrol.Service;

import com.example.storecontrol.Model.Perfil;
import com.example.storecontrol.Repository.PerfilRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public Perfil salvarPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public Optional<Perfil> buscarPerfilPorId(Long id) {
        return perfilRepository.findById(id);
    }

    public List<Perfil> listarPerfis() {
        return perfilRepository.findAll();
    }

    public void deletarPerfil(Long id) {
        perfilRepository.deleteById(id);
    }

    public List<Perfil> buscarPerfisPorUsuarioId(Long usuarioId) {
        return perfilRepository.findByUsuarioId(usuarioId);
    }
}
