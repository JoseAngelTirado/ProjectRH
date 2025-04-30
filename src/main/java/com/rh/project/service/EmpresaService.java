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

    public List<Empresa> listEmpresas() {
        return empresaRepo.findAll();
    }

    public Optional<Empresa> getEmpresaById(Long id) {
        return empresaRepo.findById(id);
    }

    public Empresa saveEmpresa(Empresa empresa) {
        return empresaRepo.save(empresa);
    }

    public void deleteEmpresa(Long id) {
        empresaRepo.deleteById(id);
    }

    public Empresa updateEmpresa(Empresa empresa) {
        return empresaRepo.save(empresa);
    }
}
