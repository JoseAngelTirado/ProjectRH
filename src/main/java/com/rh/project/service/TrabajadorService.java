package com.rh.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.rh.project.model.Trabajador;
import com.rh.project.repository.TrabajadorRepo;


@Service
public class TrabajadorService {
    private final TrabajadorRepo trabajadorRepo;

    public TrabajadorService(TrabajadorRepo trabajadorRepo) {
        this.trabajadorRepo = trabajadorRepo;
    }

    public List<Trabajador> listTrabajadores() {
        return trabajadorRepo.findAll();
    }

    public Optional<Trabajador> getTrabajadorById(Long id) {
        return trabajadorRepo.findById(id);
    }

    public Trabajador saveTrabajador(Trabajador trabajador) {
        return trabajadorRepo.save(trabajador);
    }

    public void deleteTrabajador(Long id) {
        trabajadorRepo.deleteById(id);
    }

    public Trabajador updateTrabajador(Trabajador trabajador) {
        return trabajadorRepo.save(trabajador);
    }
}
