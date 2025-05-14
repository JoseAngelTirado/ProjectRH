package com.rh.project.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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

    // ✅ Actualización parcial (PATCH)
    public Trabajador updateTrabajadorParcial(Long id, Map<String, Object> camposActualizados) {
        Optional<Trabajador> optionalTrabajador = trabajadorRepo.findById(id);
        if (optionalTrabajador.isPresent()) {
            Trabajador trabajador = optionalTrabajador.get();

            // Iteramos sobre los campos enviados para actualizar dinámicamente
            camposActualizados.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Trabajador.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, trabajador, value);
                }
            });

            // Guardamos los cambios en la base de datos
            return trabajadorRepo.save(trabajador);
        }

        return null; // Si no existe, devuelve null (lo puedes cambiar por una excepción si quieres)
    }
}
