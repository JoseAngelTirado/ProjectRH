package com.rh.project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.rh.project.model.Usuario;
import com.rh.project.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listarUsuario() {
        return usuarioService.listUsuarios();
    }
    
    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id).orElse(null);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario u){
        return usuarioService.saveUsuario(u);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId_usuario(id);
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

}
