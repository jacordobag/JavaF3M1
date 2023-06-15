package modulo1fase3.proyecto.controllers;

import jakarta.validation.Valid;
import modulo1fase3.proyecto.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @GetMapping(value="/{clienteId}",
            produces = { "application/json", "application/xml" })
    public ResponseEntity<Cliente> getCliente(@PathVariable("clienteId") long clienteId){
        Cliente cliente = new Cliente();
        cliente.setNombre("nombre del cliente:");
        cliente.setId(clienteId);
        cliente.setCorreoContacto("a@a.c");
        cliente.setNumeroEmpleados("20");
        cliente.setDireccion("la prueba de direccion");

        return ResponseEntity.ok(cliente);
    }
    /*
        @GetMapping
        public ResponseEntity <List<Cliente>> getClientes(){
            return

        }
    */
    @PostMapping
    public ResponseEntity<Void> creaCliente(@Valid @RequestBody Cliente cliente){
        return ResponseEntity.created(URI.create("1")).build();
    }

    /*

    @PutMapping("/{clienteId}")
    public ResponseEntity<Void> actualizaCliente(@PathVariable Long clienteId, @RequestBody Cliente cliente){

    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminaCliente(@PathVariable Long clienteId){

    }*/
}
