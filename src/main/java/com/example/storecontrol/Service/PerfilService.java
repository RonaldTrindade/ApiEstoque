package com.example.storecontrol.Service;

import com.example.storecontrol.Model.Perfil;

import java.util.List;
import java.util.Optional;

public interface PerfilService {
    Perfil salvarPerfil(Perfil perfil);
    Optional<Perfil> buscarPerfilPorId(Long id);
    List<Perfil> listarPerfis();
    void deletarPerfil(Long id);
    List<Perfil> buscarPerfisPorUsuarioId(Long usuarioId);
}
