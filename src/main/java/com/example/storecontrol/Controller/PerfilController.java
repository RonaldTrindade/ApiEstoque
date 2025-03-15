package com.example.storecontrol.Controller;

import com.example.storecontrol.Model.Perfil;
import com.example.storecontrol.Service.PerfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfis")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @PostMapping
    public ResponseEntity<Perfil> salvarPerfil(@RequestBody Perfil perfil) {
        Perfil perfilSalvo = perfilService.salvarPerfil(perfil);
        return ResponseEntity.ok(perfilSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPerfilPorId(@PathVariable Long id) {
        return perfilService.buscarPerfilPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> listarPerfis() {
        List<Perfil> perfis = perfilService.listarPerfis();
        return ResponseEntity.ok(perfis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPerfil(@PathVariable Long id) {
        perfilService.deletarPerfil(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Perfil>> buscarPerfisPorUsuarioId(@PathVariable Long usuarioId) {
        List<Perfil> perfis = perfilService.buscarPerfisPorUsuarioId(usuarioId);
        return ResponseEntity.ok(perfis);
    }
}
