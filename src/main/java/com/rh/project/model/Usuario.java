package com.rh.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id_usuario;

    private String nombre;
    private String email;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol{
        dirctivo, rh , trabajador
    }

}
