package com.rh.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rh.project.model.Expediente;
import com.rh.project.service.ExpedienteService;

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
