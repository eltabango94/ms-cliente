package com.devsu.mscliente.modulos.cliente;

import com.devsu.mscliente.dto.ClienteDTO;

import java.util.List;


public interface ClienteService {
    ClienteDTO crearCliente(ClienteDTO clienteDTO);

    List<ClienteDTO> obtenerTodos();

    ClienteDTO obtenerPorId(Long id);

    ClienteDTO obtenerPorClienteId(String clienteId);

    ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO);

    void eliminarCliente(Long id);
}
