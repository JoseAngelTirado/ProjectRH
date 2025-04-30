package com.rh.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.project.model.Documento;

public interface DocumentoRepo extends JpaRepository<Documento, Long> {


}
