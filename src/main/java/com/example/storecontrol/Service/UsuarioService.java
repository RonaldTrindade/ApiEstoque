package com.example.storecontrol.Service;

import com.example.storecontrol.Dto.RegistroUsuarioDTO;
import com.example.storecontrol.Model.Usuario;
import com.example.storecontrol.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deletarUsuario(Long id, String emailUsuarioAutenticado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        if (!usuario.getEmail().equals(emailUsuarioAutenticado)) {
            throw new RuntimeException("Você não tem permissão para excluir esta conta.");
        }
        usuarioRepository.deleteById(id);
    }

    public boolean autenticarUsuario(String email, String senha) {
        return usuarioRepository.findByEmail(email)
                .map(usuario -> passwordEncoder.matches(senha, usuario.getSenha()))
                .orElse(false);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado com o email: " + email));
    }
}
