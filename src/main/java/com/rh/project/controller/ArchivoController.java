package com.rh.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    private final Path fileStorageLocation;

    // Inyecta la ruta desde tus propiedades (ej: application.properties)
    public ArchivoController(
            ArchivosGuardadosService archivosService,
            @Value("${file.upload-dir}") String uploadDir) {

        this.archivosService = archivosService;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation); // Crea el directorio si no existe
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio: " + uploadDir, e);
        }
    }

    @GetMapping("/listar/{idTrabajador}")
    public ResponseEntity<List<String>> listarArchivosTrabajador(
            @PathVariable String idTrabajador) {

        try {
            if (!Files.exists(this.fileStorageLocation)) {
                return ResponseEntity.notFound().build();
            }

            try (Stream<Path> paths = Files.list(this.fileStorageLocation)) {
                List<String> archivos = paths
                        .filter(Files::isRegularFile) // Solo archivos (no directorios)
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .filter(nombre -> nombre.startsWith(idTrabajador + "_"))
                        .collect(Collectors.toList());

                return ResponseEntity.ok(archivos);
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Endpoint para subir archivo
    @PostMapping("/subir")
    public ResponseEntity<String> subirArchivo(
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam(value = "prefijo", required = false) String prefijo) {

        String nombreArchivo = archivosService.guardarArchivo(archivo, prefijo);
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
