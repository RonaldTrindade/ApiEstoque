package com.example.storecontrol.Service.impl;

import com.example.storecontrol.Dto.RegistroUsuarioDTO;
import com.example.storecontrol.Model.Usuario;
import com.example.storecontrol.Repository.UsuarioRepository;
import com.example.storecontrol.Service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Usuario registrarUsuario(RegistroUsuarioDTO registroUsuarioDTO) {
        if (usuarioRepository.existsByEmail(registroUsuarioDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado: " + registroUsuarioDTO.getEmail());
        }
        Usuario usuario = new Usuario();
        usuario.setNome(registroUsuarioDTO.getNome());
        usuario.setEmail(registroUsuarioDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(registroUsuarioDTO.getSenha()));
        return usuarioRepository.save(usuario);
    }
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    @Override
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    @Override
    public void deletarUsuario(String emailUsuarioAutenticado) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuarioAutenticado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + emailUsuarioAutenticado));
        usuarioRepository.delete(usuario);
    }
    @Override
    public boolean autenticarUsuario(String email, String senha) {
        return usuarioRepository.findByEmail(email)
                .map(usuario -> passwordEncoder.matches(senha, usuario.getSenha()))
                .orElse(false);
    }
    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado com o email: " + email));
    }
}
