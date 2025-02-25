package com.devsu.mscliente.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTO {
    private String nombre;
    private String identificacion;
    private String telefono;
    private String clienteId;
    private Boolean estado;
}