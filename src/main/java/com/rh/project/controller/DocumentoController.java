package com.rh.project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.rh.project.model.Documento;
import com.rh.project.service.DocumentoService;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    // Listar todos los documentos
    @GetMapping
    public List<Documento> listarDocumentos() {
        return documentoService.listDocumentos();
    }

    // Obtener un documento por ID
    @GetMapping("/{id}")
    public Documento getDocumentoById(@PathVariable Long id) {
        return documentoService.getDocumentoById(id).orElse(null);
    }

    // Crear un nuevo documento
    @PostMapping
    public Documento crearDocumento(@RequestBody Documento documento) {
        return documentoService.saveDocumento(documento);
    }

    // Actualizar un documento existente
    @PutMapping("/{id}")
    public Documento actualizarDocumento(@PathVariable Long id, @RequestBody Documento documento) {
        documento.setId_documento(id);
        return documentoService.updateDocumento(documento);
    }

    // Eliminar un documento por su ID
    @DeleteMapping("/{id}")
    public void eliminarDocumento(@PathVariable Long id) {
        documentoService.deleteDocumento(id);
    }
}
