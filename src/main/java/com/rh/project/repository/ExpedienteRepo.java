package com.rh.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.project.model.Expediente;

public interface ExpedienteRepo extends JpaRepository<Expediente, Long> {
}

