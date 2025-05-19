package com.rh.project.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rh.project.service.ArchivosGuardadosService;

@RestController
@RequestMapping("/archivos")
@CrossOrigin(origins = "http://localhost:5173")
public class ArchivoController {

    private final ArchivosGuardadosService archivosService;

    public ArchivoController(ArchivosGuardadosService archivosService) {
        this.archivosService = archivosService;
    }

    // Endpoint para subir archivo
    @PostMapping("/subir")
    public ResponseEntity<String> subirArchivo(@RequestParam("archivo") MultipartFile archivo) {
        String nombreArchivo = archivosService.guardarArchivo(archivo);
        return ResponseEntity.ok("Archivo guardado: " + nombreArchivo);
    }

    // Endpoint para descargar archivo
    @GetMapping("/descargar/{nombreArchivo:.+}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable String nombreArchivo) {
        Resource recurso = archivosService.cargarArchivo(nombreArchivo);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }
}
