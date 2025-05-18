package com.rh.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.project.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
    
    //Lograr queries condicionales 
    @Query("SELECT u FROM Usuario u WHERE u.empresa.id_empresa = :empresaId")
    List<Usuario> findUsuariosByEmpresaId(@Param("empresaId") Long empresaId);

    @Query("SELECT u FROM Usuario u")
    List<Usuario> findAllUsuarios();
}
