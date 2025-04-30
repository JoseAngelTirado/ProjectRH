package com.rh.project.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "documento")
@Data
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_documento;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    private Expediente expediente;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String archivoPath;
    private LocalDate fechaSubida;

    public enum Tipo{
        acta_nacimiento, rfc, nss, curp
    }


}
