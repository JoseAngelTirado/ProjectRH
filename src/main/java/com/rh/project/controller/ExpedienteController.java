package com.rh.project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.rh.project.model.Expediente;
import com.rh.project.service.ExpedienteService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/expedientes")
public class ExpedienteController {

    private final ExpedienteService expedienteService;
    
    public ExpedienteController(ExpedienteService expedienteService){
        this.expedienteService = expedienteService;
    }

    // Obtener todos los expedientes
    @GetMapping
    public List<Expediente> listarExpedientes(){
        return expedienteService.listExpediente();
    }

    // Obtener expediente por ID
    @GetMapping("/{id}")
    public Expediente getExpedienteById(@PathVariable Long id){
        return expedienteService.getExpedienteById(id).orElse(null);
    }

    // Crear expediente
    @PostMapping
        public Expediente crearExpediente(@RequestBody Expediente expediente){
        return expedienteService.saveExpediente(expediente);
    }



    // Actualizar expediente
    @PutMapping("/{id}")
    public Expediente actualizarExpediente(@PathVariable Long id, @RequestBody Expediente expediente){
        expediente.setId_expediente(id);
        return expedienteService.updateExpediente(expediente);
    }

    // Eliminar expediente
    @DeleteMapping("/{id}")
    public void eliminarExpediente(@PathVariable Long id){
        expedienteService.deleteExpediente(id);
    }
}
