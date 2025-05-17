package com.rh.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.project.model.Usuario;
import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
 
    Optional<Usuario> findByEmail(String email);

}
