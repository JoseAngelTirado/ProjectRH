package com.rh.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.rh.project.model.Expediente;
import com.rh.project.repository.ExpedienteRepo;

@Service
public class ExpedienteService {

    private final ExpedienteRepo expedienteRepo;

    public ExpedienteService(ExpedienteRepo expedienteRepo){
        this.expedienteRepo = expedienteRepo;
    }

    public List<Expediente> listExpediente(){
        return expedienteRepo.findAll();
    }

    public Optional<Expediente> getExpedienteById(Long id){
        return expedienteRepo.findById(id);
    }

    public void deleteExpediente(Long id){
        expedienteRepo.deleteById(id);
    }
    public Expediente saveExpediente(Expediente expediente){
        return expedienteRepo.save(expediente);
    }
    public Expediente updateExpediente(Expediente expediente){
        return expedienteRepo.save(expediente);
    }
}
