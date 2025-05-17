package com.rh.project.service;

import java.util.List;
import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh.project.model.Usuario;
import com.rh.project.repository.UsuarioRepo;


@Service
public class UsuarioService {

    private final UsuarioRepo usuarioRepo;

    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public List<Usuario> listUsuarios() {
        return usuarioRepo.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepo.findById(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }

    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

}
