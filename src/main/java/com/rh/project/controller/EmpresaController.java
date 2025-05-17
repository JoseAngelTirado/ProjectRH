package com.rh.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rh.project.model.Empresa;
import com.rh.project.service.EmpresaService;
@CrossOrigin(origins = "http://localhost:5173")

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    // Listar todas las empresas
    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaService.listEmpresas();
    }

    // Obtener una empresa por su ID
    @GetMapping("/{id}")
    public Empresa obtenerEmpresaPorId(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.getEmpresaById(id);
        return empresa.orElse(null);
    }

    // Crear una nueva empresa
    @PostMapping
    public Empresa crearEmpresa(@RequestBody Empresa empresa) {
        return empresaService.saveEmpresa(empresa);
    }

    // Actualizar una empresa existente
    @PutMapping("/{id}")
    public Empresa actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        empresa.setId_empresa(id);
        return empresaService.updateEmpresa(empresa);
    }

    // Eliminar una empresa por su ID
    @DeleteMapping("/{id}")
    public void eliminarEmpresa(@PathVariable Long id) {
        empresaService.deleteEmpresa(id);
    }
}
