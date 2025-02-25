package com.devsu.mscliente.modulos.cliente;

import com.devsu.mscliente.dto.ClienteDTO;
import com.devsu.mscliente.entidades.Cliente;
import com.devsu.mscliente.mappers.ClienteMapper;
import com.devsu.mscliente.utils.RecursoNoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        log.info("Creando nuevo cliente: {}", clienteDTO.getClienteId());
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(clienteGuardado);
    }

    @Override
    public List<ClienteDTO> obtenerTodos() {
        log.info("Obteniendo todos los clientes");
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO obtenerPorId(Long id) {
        log.info("Buscando cliente por ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Cliente no encontrado con ID: {}", id);
                    return new RecursoNoEncontradoException("Cliente no encontrado con ID: " + id);
                });

        return clienteMapper.toResponseDTO(cliente);
    }

    @Override
    public ClienteDTO obtenerPorClienteId(String clienteId) {
        log.info("Buscando cliente por clienteId: {}", clienteId);
        Cliente cliente = clienteRepository.findByClienteId(clienteId)
                .orElseThrow(() -> {
                    log.error("Cliente no encontrado con clienteId: {}", clienteId);
                    return new RecursoNoEncontradoException("Cliente no encontrado con clienteId: " + clienteId);
                });

        return clienteMapper.toResponseDTO(cliente);
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        log.info("Actualizando cliente con ID: {}", id);
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombre(clienteDTO.getNombre());
                    cliente.setGenero(clienteDTO.getGenero());
                    cliente.setEdad(clienteDTO.getEdad());
                    cliente.setIdentificacion(clienteDTO.getIdentificacion());
                    cliente.setDireccion(clienteDTO.getDireccion());
                    cliente.setTelefono(clienteDTO.getTelefono());
                    cliente.setClienteId(clienteDTO.getClienteId());
                    cliente.setContrasenia(clienteDTO.getContrasenia());
                    cliente.setEstado(clienteDTO.getEstado());

                    Cliente actualizado = clienteRepository.save(cliente);
                    return clienteMapper.toResponseDTO(actualizado);
                })
                .orElseThrow(() -> {
                    log.error("No se encontró el cliente con ID: {}", id);
                    return new RecursoNoEncontradoException("Cliente no encontrado con ID: " + id);
                });
    }

    @Override
    public void eliminarCliente(Long id) {
        log.info("Eliminando cliente con ID: {}", id);
        if (!clienteRepository.existsById(id)) {
            log.error("No se pudo eliminar, cliente no encontrado con ID: {}", id);
            throw new RecursoNoEncontradoException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
        log.info("Cliente con ID {} eliminado correctamente", id);
    }
}