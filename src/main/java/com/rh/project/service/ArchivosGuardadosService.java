package com.rh.project.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArchivosGuardadosService {

    private final Path fileStorageLocation;

    // Inyectamos la propiedad desde application.properties
    public ArchivosGuardadosService(@Value("${file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("No se puede crear el directorio donde se guardan los archivos", ex);
        }
    }

    public String guardarArchivo(MultipartFile archivo, String prefijo) {
        String extension = obtenerExtension(archivo.getOriginalFilename());
        String nombreArchivo = (prefijo != null ? prefijo + "_" : "") +
                System.currentTimeMillis() + "." + extension;
        try {
            if (nombreArchivo.contains("..")) {
                throw new RuntimeException("Nombre de archivo inválido: " + nombreArchivo);
            }
            Path destino = this.fileStorageLocation.resolve(nombreArchivo);
            Files.copy(archivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
            return nombreArchivo;
        } catch (IOException ex) {
            throw new RuntimeException("No se pudo guardar el archivo: " + nombreArchivo, ex);
        }
    }

    private String obtenerExtension(String nombreArchivo) {
        try {
            return nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    // Método para cargar un archivo
    public Resource cargarArchivo(String nombreArchivo) {
        try {
            Path archivoPath = this.fileStorageLocation.resolve(nombreArchivo).normalize();
            Resource recurso = new UrlResource(archivoPath.toUri());
            if (recurso.exists()) {
                return recurso;
            } else {
                throw new RuntimeException("Archivo no encontrado: " + nombreArchivo);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Error al cargar el archivo: " + nombreArchivo, ex);
        }
    }
}
