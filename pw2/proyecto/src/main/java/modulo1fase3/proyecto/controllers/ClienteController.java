package modulo1fase3.proyecto.controllers;

import modulo1fase3.proyecto.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long clienteId){

    }

    @GetMapping
    public ResponseEntity <List<Cliente>> getClientes(){

    }

    @PostMapping
    public ResponseEntity<Void> creaCliente(@RequestBody Cliente cliente){

    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Void> actualizaCliente(@PathVariable Long clienteId, @RequestBody Cliente cliente){

    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminaCliente(@PathVariable Long clienteId){

    }
}
