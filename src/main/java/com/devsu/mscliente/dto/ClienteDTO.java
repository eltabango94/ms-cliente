package com.devsu.mscliente.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El género no puede estar vacío")
    private String genero;

    @Min(value = 0, message = "La edad debe ser un número positivo")
    private int edad;

    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    @NotBlank(message = "El clienteId es obligatorio")
    private String clienteId;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasenia;

    private Boolean estado;
}