package com.rh.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.project.model.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
 
}
