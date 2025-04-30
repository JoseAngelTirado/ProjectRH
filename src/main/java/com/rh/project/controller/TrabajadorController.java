package com.rh.project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.rh.project.model.Trabajador;
import com.rh.project.service.TrabajadorService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/trabajadores")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    public TrabajadorController(TrabajadorService trabajadorService){
        this.trabajadorService = trabajadorService;
    }

    @GetMapping
    public List<Trabajador> listarTrabajador(){
        return trabajadorService.listTrabajadores();
    }

    @GetMapping("{id}")
    public Trabajador getTrabajadorById(@PathVariable Long id){
        return trabajadorService.getTrabajadorById(id).orElse(null);
    }

    @PostMapping
    public Trabajador crearTrabajdor(@RequestBody Long id){
        return trabajadorService.getTrabajadorById(id).orElse(null);
    }

    @PostMapping("/{id}")
    public Trabajador actualizarTrabajador(@PathVariable Long id, @RequestBody Trabajador trabajador){
        trabajador.setId_trabajador(id);
        return trabajadorService.updateTrabajador(trabajador);
    }

    @DeleteMapping("/{id}")
    public void eliminarTrabajador(@PathVariable Long id){
        trabajadorService.deleteTrabajador(id);
    }
}
