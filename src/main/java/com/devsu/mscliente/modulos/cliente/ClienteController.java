package com.devsu.mscliente.modulos.cliente;

import com.devsu.mscliente.dto.ClienteDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO nuevoCliente = clienteService.crearCliente(clienteDTO);
        return ResponseEntity.ok(nuevoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodos() {
        List<ClienteDTO> clientes = clienteService.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/buscar/{clienteId}")
    public ResponseEntity<ClienteDTO> obtenerPorClienteId(@PathVariable String clienteId) {
        ClienteDTO cliente = clienteService.obtenerPorClienteId(clienteId);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteActualizado = clienteService.actualizarCliente(id, clienteDTO);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}