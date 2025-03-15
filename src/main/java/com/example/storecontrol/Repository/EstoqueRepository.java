package com.example.storecontrol.Repository;

import com.example.storecontrol.Model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    List<Estoque> findByPerfilId(Long perfilId);
}
