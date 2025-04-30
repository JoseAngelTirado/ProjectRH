package com.rh.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.project.model.Trabajador;

public interface TrabajadorRepo extends JpaRepository<Trabajador, Long> {

}
