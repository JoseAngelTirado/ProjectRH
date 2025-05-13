package com.rh.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rh.project.model.Empresa;
import com.rh.project.repository.EmpresaRepo;

@Service
public class EmpresaService {

    private final EmpresaRepo empresaRepo;

    public EmpresaService(EmpresaRepo empresaRepo) {
        this.empresaRepo = empresaRepo;
    }

    // Listar todas las empresas
    public List<Empresa> listEmpresas() {
        return empresaRepo.findAll();
    }

    // Obtener una empresa por su ID
    public Optional<Empresa> getEmpresaById(Long id) {
        return empresaRepo.findById(id);
    }

    // Guardar una nueva empresa
    public Empresa saveEmpresa(Empresa empresa) {
        return empresaRepo.save(empresa);
    }

    // Actualizar una empresa existente
    public Empresa updateEmpresa(Empresa empresa) {
        return empresaRepo.save(empresa);
    }

    // Eliminar una empresa por su ID
    public void deleteEmpresa(Long id) {
        empresaRepo.deleteById(id);
    }
}
