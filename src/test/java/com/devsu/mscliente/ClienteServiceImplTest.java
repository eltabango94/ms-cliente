package com.devsu.mscliente;

import com.devsu.mscliente.dto.ClienteDTO;
import com.devsu.mscliente.entidades.Cliente;
import com.devsu.mscliente.mappers.ClienteMapper;
import com.devsu.mscliente.modulos.cliente.ClienteRepository;
import com.devsu.mscliente.modulos.cliente.ClienteServiceImpl;
import com.devsu.mscliente.utils.RecursoNoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Jose Lema");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setIdentificacion("1234567890");
        cliente.setDireccion("Otavalo sn y principal");
        cliente.setTelefono("098254785");
        cliente.setClienteId("CL001");
        cliente.setContrasenia("1234");
        cliente.setEstado(true);

        clienteDTO = new ClienteDTO();
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setGenero(cliente.getGenero());
        clienteDTO.setEdad(cliente.getEdad());
        clienteDTO.setIdentificacion(cliente.getIdentificacion());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setClienteId(cliente.getClienteId());
        clienteDTO.setContrasenia(cliente.getContrasenia());
        clienteDTO.setEstado(cliente.getEstado());
    }

    @Test
    void testCrearCliente() {
        when(clienteMapper.toEntity(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toResponseDTO(cliente)).thenReturn(clienteDTO);
        ClienteDTO resultado = clienteService.crearCliente(clienteDTO);
        assertNotNull(resultado);
        assertEquals(clienteDTO.getClienteId(), resultado.getClienteId());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testObtenerTodos() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        when(clienteMapper.toResponseDTO(cliente)).thenReturn(clienteDTO);
        List<ClienteDTO> resultado = clienteService.obtenerTodos();
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId_ClienteExiste() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteMapper.toResponseDTO(cliente)).thenReturn(clienteDTO);
        ClienteDTO resultado = clienteService.obtenerPorId(1L);
        assertNotNull(resultado);
        assertEquals("Jose Lema", resultado.getNombre());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerPorId_ClienteNoExiste() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () -> clienteService.obtenerPorId(1L));
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testActualizarCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toResponseDTO(cliente)).thenReturn(clienteDTO);
        ClienteDTO resultado = clienteService.actualizarCliente(1L, clienteDTO);
        assertNotNull(resultado);
        assertEquals(clienteDTO.getClienteId(), resultado.getClienteId());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testActualizarCliente_ClienteNoExiste() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RecursoNoEncontradoException.class, () -> clienteService.actualizarCliente(1L, clienteDTO));
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testEliminarCliente_ClienteExiste() {
        when(clienteRepository.existsById(1L)).thenReturn(true);
        clienteService.eliminarCliente(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testEliminarCliente_ClienteNoExiste() {
        when(clienteRepository.existsById(1L)).thenReturn(false);
        assertThrows(RecursoNoEncontradoException.class, () -> clienteService.eliminarCliente(1L));
        verify(clienteRepository, times(1)).existsById(1L);
    }
}