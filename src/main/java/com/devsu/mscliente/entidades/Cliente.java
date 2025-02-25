package com.devsu.mscliente.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente extends Persona {

    @Column(unique = true, nullable = false)
    private String clienteId;

    private String contrasenia;

    private Boolean estado;
}