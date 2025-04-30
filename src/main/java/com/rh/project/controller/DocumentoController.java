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

    @GetMapping
    public List<Documento> lsitarDocumento(){
        return documentoService.listDocumentos();
    }

    @GetMapping("/{id}")
    public Documento getDocumentoById(@PathVariable Long id){
        return documentoService.getDocumentoById(id).orElse(null);
    }

    @PostMapping
    public Documento crearDocumento(@RequestBody Long id){
        return documentoService.getDocumentoById(id).orElse(null);
    }

    @PostMapping("/{id}")
    public Documento actualizarDocumento(@PathVariable Long id, @RequestBody Documento documento){
        documento.setId_documento(id);
        return documentoService.updateDocumento(documento);
    }

    @DeleteMapping("/{id}")
    public void eliminarDocumento(@PathVariable Long id){
        documentoService.deleteDocumento(id);
    }

    
}
