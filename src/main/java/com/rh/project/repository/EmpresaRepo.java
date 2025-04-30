package com.rh.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.project.model.Empresa;

public interface EmpresaRepo extends JpaRepository<Empresa, Long> {

}
