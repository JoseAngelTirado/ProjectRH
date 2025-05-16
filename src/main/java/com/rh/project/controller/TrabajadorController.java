package com.rh.project.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rh.project.model.Trabajador;
import com.rh.project.service.TrabajadorService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/trabajadores")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    public TrabajadorController(TrabajadorService trabajadorService){
        this.trabajadorService = trabajadorService;
    }

    // Listar todos los trabajadores
    @GetMapping
    public List<Trabajador> listarTrabajadores(){
        return trabajadorService.listTrabajadores();
    }

    // Obtener un trabajador por ID
    @GetMapping("/{id}")
    public Trabajador getTrabajadorById(@PathVariable Long id){
        return trabajadorService.getTrabajadorById(id).orElse(null);
    }

    // Crear un nuevo trabajador
    @PostMapping
    public Trabajador crearTrabajador(@RequestBody Trabajador trabajador){
        return trabajadorService.saveTrabajador(trabajador);
    }

    // Actualizar un trabajador existente (completo)
    @PutMapping("/{id}")
    public Trabajador actualizarTrabajador(@PathVariable Long id, @RequestBody Trabajador trabajador){
        trabajador.setId_trabajador(id);
        return trabajadorService.updateTrabajador(trabajador);
    }

    // Actualizar parcialmente un trabajador (solo campos específicos)
    @PatchMapping("/{id}")
    public ResponseEntity<Trabajador> actualizarTrabajadorParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> camposActualizados) {

        Trabajador trabajadorActualizado = trabajadorService.updateTrabajadorParcial(id, camposActualizados);
        if (trabajadorActualizado != null) {
            return ResponseEntity.ok(trabajadorActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un trabajador por ID
    @DeleteMapping("/{id}")
    public void eliminarTrabajador(@PathVariable Long id){
        trabajadorService.deleteTrabajador(id);
    }
}
