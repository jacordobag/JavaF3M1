package modulo1fase3.proyecto.controllers;

import modulo1fase3.proyecto.model.Cliente;
import modulo1fase3.proyecto.model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController  {

    @GetMapping("/{productoId}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long productoId){

    }

    @GetMapping
    public ResponseEntity <List<Producto>> getProductos(){

    }

    @PostMapping
    public ResponseEntity<Void> creaProducto(@RequestBody Producto producto){

    }

    @PutMapping("/{productoId}")
    public ResponseEntity<Void> actualizaProducto(@PathVariable Long productoId, @RequestBody Producto producto){

    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity<Void> eliminaProducto(@PathVariable Long productoId){

    }
}
