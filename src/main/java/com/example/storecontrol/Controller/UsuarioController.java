package com.example.storecontrol.Controller;

import com.example.storecontrol.Dto.LoginDTO;
import com.example.storecontrol.Dto.RegistroUsuarioDTO;
import com.example.storecontrol.Dto.UsuarioDTO;
import com.example.storecontrol.Model.Usuario;
import com.example.storecontrol.Repository.UsuarioRepository;
import com.example.storecontrol.Service.impl.UsuarioServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    private final UsuarioServiceImpl usuarioService;
    private final UsuarioRepository usuarioRepository;


    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegistroUsuarioDTO registroUsuarioDTO) {
        try {
            Usuario usuarioRegistrado = usuarioService.registrarUsuario(registroUsuarioDTO);
            UsuarioDTO usuarioDTO = mapToUsuarioDTO(usuarioRegistrado);
            return ResponseEntity.ok(usuarioDTO);
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            boolean autenticado = usuarioService.autenticarUsuario(loginDTO.getEmail(), loginDTO.getSenha());
            if (autenticado) {
                Usuario usuario = usuarioService.buscarUsuarioPorEmail(loginDTO.getEmail());
                UsuarioDTO usuarioDTO = mapToUsuarioDTO(usuario);
                return ResponseEntity.ok(usuarioDTO);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Email ou senha incorretos.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado com o email: " + loginDTO.getEmail());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return ResponseEntity.ok("Logout realizado com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nenhum usuário autenticado.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id)
                .map(this::mapToUsuarioDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios()
                .stream()
                .map(this::mapToUsuarioDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuario(Principal principal, HttpServletRequest request, HttpServletResponse response) {
        String emailUsuarioAutenticado = principal.getName();
        usuarioService.deletarUsuario(emailUsuarioAutenticado);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@PathVariable String email) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
            UsuarioDTO usuarioDTO = mapToUsuarioDTO(usuario);
            return ResponseEntity.ok(usuarioDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    private UsuarioDTO mapToUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        return usuarioDTO;
    }
}
