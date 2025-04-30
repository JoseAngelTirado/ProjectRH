package com.rh.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rh.project.model.Documento;
import com.rh.project.repository.DocumentoRepo;

@Service
public class DocumentoService {
    private final DocumentoRepo documentoRepo;

    public DocumentoService(DocumentoRepo documentoRepo) {
        this.documentoRepo = documentoRepo;
    }

    public List<Documento> listDocumentos() {
        return documentoRepo.findAll();
    }

    public Optional<Documento> getDocumentoById(Long id) {
        return documentoRepo.findById(id);
    }

    public Documento saveDocumento(Documento documento) {
        return documentoRepo.save(documento);
    }

    public void deleteDocumento(Long id) {
        documentoRepo.deleteById(id);
    }

    public Documento updateDocumento(Documento documento) {
        return documentoRepo.save(documento);
    }
}
