package com.rh.project.model;

import java.time.LocalDate;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "expediente")
@Data
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_expediente;

    private String rfc_empresa;
    private String rfc_trabajador;
    private String nss;
    private Integer antiguedad;
    private LocalDate fechaRegistro;
    private BigDecimal sueldo;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "id_trabajador")
    private Trabajador trabajador;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
}
