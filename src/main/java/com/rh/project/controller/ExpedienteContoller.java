package com.rh.project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.rh.project.model.Expediente;
import com.rh.project.service.ExpedienteService;


@RestController
@RequestMapping("/expedientes")
public class ExpedienteContoller {

    private final ExpedienteService expedienteService;
    
    public ExpedienteContoller(ExpedienteService expedienteService){
        this.expedienteService = expedienteService;
    }

    @GetMapping
    public List<Expediente> listarExpedientes(){
        return expedienteService.listExpediente();
    }

    @GetMapping("/{id}")
    public Expediente getExpedienteById(@PathVariable Long id){
        return expedienteService.getExpedienteById(id).orElse(null);
    }

    @PostMapping
    public Expediente crearExpediente(@RequestBody Long id){
        return expedienteService.getExpedienteById(id).orElse(null);
    }

    @PostMapping("/{id}")
    public Expediente actualizarExpediente(@PathVariable Long id, @RequestBody Expediente expediente){
        expediente.setId_expediente(id);
        return expedienteService.updateExpediente(expediente);
    }

    @DeleteMapping("/{id}")
    public void eliminarExpediente(@PathVariable Long id){
        expedienteService.deleteExpediente(id);
    }


}
