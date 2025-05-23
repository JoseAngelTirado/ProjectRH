package com.rh.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "empresa")
@Data
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long id_empresa;

    private String razon_social;
    private String rfc_empresa;
    private String direccion;
    private String giro_empresarial;
    private String regimen_fiscal;
    private String telefono;

}
