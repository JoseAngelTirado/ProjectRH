package com.rh.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "trabajador")
@Data
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_trabajador;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String telefono;
    private String rfc_trabajador;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;
}
