package com.devsu.mscliente.entidades;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String genero;
    private int edad;

    @Column(unique = true, nullable = false)
    private String identificacion;

    private String direccion;
    private String telefono;
}