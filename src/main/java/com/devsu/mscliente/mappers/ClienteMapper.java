package com.devsu.mscliente.mappers;

import com.devsu.mscliente.dto.ClienteDTO;
import com.devsu.mscliente.entidades.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setGenero(dto.getGenero());
        cliente.setEdad(dto.getEdad());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setClienteId(dto.getClienteId());
        cliente.setContrasenia(dto.getContrasenia());
        cliente.setEstado(dto.getEstado());
        return cliente;
    }

    public ClienteDTO toResponseDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre(cliente.getNombre());
        dto.setIdentificacion(cliente.getIdentificacion());
        dto.setTelefono(cliente.getTelefono());
        dto.setClienteId(cliente.getClienteId());
        dto.setEstado(cliente.getEstado());
        dto.setGenero(cliente.getGenero());
        dto.setEdad(cliente.getEdad());
        dto.setDireccion(cliente.getDireccion());
        return dto;
    }
}