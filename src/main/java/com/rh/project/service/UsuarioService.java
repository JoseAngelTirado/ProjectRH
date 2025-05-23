package com.rh.project.service;

import java.util.List;
import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rh.project.model.Expediente;
import com.rh.project.model.Trabajador;
import com.rh.project.model.Usuario;
import com.rh.project.repository.ExpedienteRepo;
import com.rh.project.repository.TrabajadorRepo;
import com.rh.project.repository.UsuarioRepo;

@Service
public class UsuarioService {

    private final UsuarioRepo usuarioRepo;
    private final TrabajadorRepo trabajadorRepo;
    private final ExpedienteRepo expedienteRepo;

    //Inyecion de repositorios
    public UsuarioService(UsuarioRepo usuarioRepo, TrabajadorRepo trabajadorRepo, ExpedienteRepo expedienteRepo) {
        this.usuarioRepo = usuarioRepo;
        this.trabajadorRepo = trabajadorRepo;
        this.expedienteRepo = expedienteRepo;
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

    @Transactional
    public Usuario updateUsuario(Long id, Usuario usuarioRequest) {
        Usuario usuarioExistente = usuarioRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNombre(usuarioRequest.getNombre());
        usuarioExistente.setEmail(usuarioRequest.getEmail());
        usuarioExistente.setPassword(usuarioRequest.getPassword());
        usuarioExistente.setRol(usuarioRequest.getRol());
        // puedes actualizar otros campos aquí según necesites

        return usuarioRepo.save(usuarioExistente);
    }

    @Transactional
    public Usuario crearUsuarioConDependencias(Usuario usuario){
        //Condicional del rol, para trabajdor o no
        Usuario nuevoUsuario = usuarioRepo.save(usuario);
        if(usuario.getRol() == Usuario.Rol.trabajador){
            Trabajador trabajador = new Trabajador();
            trabajador.setId_trabajador(nuevoUsuario.getId_usuario());
            trabajador.setNombre(usuario.getNombre());
            trabajador.setUsuario(usuario);
            // Guardar el trabajador en la base de datos
            trabajadorRepo.save(trabajador);
        
            Expediente expediente = new Expediente();
            expediente.setId_expediente(trabajador.getId_trabajador());
            expediente.setTrabajador(trabajador);
            // Guardar el expediente en la base de datos
            expedienteRepo.save(expediente);
        }else {
            // guardar el usuario solo con expediente
            Expediente expediente = new Expediente();
            expediente.setId_expediente(nuevoUsuario.getId_usuario());
            expedienteRepo.save(expediente);
        }
        
    return nuevoUsuario;
    }

    //Metodo para obtener usuarios por empresa solo si el rol es director o rh
    public List<Usuario> obtenerUsuario(Usuario usuarioActual){
        if(usuarioActual.getRol() == Usuario.Rol.director || usuarioActual.getRol() == Usuario.Rol.rh){
            return usuarioRepo.findAll();
        }else {
            return usuarioRepo.findUsuariosByEmpresaId(usuarioActual.getEmpresa().getId_empresa());
        }
    }
}
