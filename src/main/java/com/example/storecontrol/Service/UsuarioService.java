package com.example.storecontrol.Service;

import com.example.storecontrol.Dto.RegistroUsuarioDTO;
import com.example.storecontrol.Model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario registrarUsuario(RegistroUsuarioDTO registroUsuarioDTO);
    List<Usuario> listarUsuarios();
    Optional<Usuario> buscarUsuarioPorId(Long id);
    void deletarUsuario(String emailUsuarioAutenticado);
    boolean autenticarUsuario(String email, String senha);
    Usuario buscarUsuarioPorEmail(String email);
}
