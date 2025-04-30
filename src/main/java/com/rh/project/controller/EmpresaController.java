package com.rh.project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.rh.project.model.Empresa;
import com.rh.project.service.EmpresaService;



@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService;
    }
    
    @GetMapping
    public List<Empresa> listarEmpresa(){
        return empresaService.listEmpresas();
    }

    @GetMapping("/{id}")
    public Empresa getEmpresaById(@PathVariable Long id){
        return empresaService.getEmpresaById(id).orElse(null);
    }

    @PostMapping
    public Empresa crearEmpresa(@RequestBody Long id){
        return empresaService.getEmpresaById(id).orElse(null);
    }

    @PostMapping("/{id}")
    public Empresa actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa){
        empresa.setId_empresa(id);
        return empresaService.updateEmpresa(empresa);
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpresa(@PathVariable Long id){
        empresaService.deleteEmpresa(id);
    }
}
