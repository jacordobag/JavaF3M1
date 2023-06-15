package modulo1fase3.proyecto.services;

import lombok.RequiredArgsConstructor;
import modulo1fase3.proyecto.controllers.mappers.ProductoMapper;
import modulo1fase3.proyecto.exceptions.ElementNotFoundException;
import modulo1fase3.proyecto.model.Producto;
import modulo1fase3.proyecto.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    public Producto guardaProducto(Producto producto) {
        return mapper.productoEntityToProductoModel(
                repository.save(mapper.productoModelToProductoEntity(producto))
        );
    }

    public List<Producto> obtenProductos(){
        return repository.findAll().stream().map(Producto -> mapper.productoEntityToProductoModel(Producto)).collect(Collectors.toList());
    }

    public Optional<Producto> obtenProducto(long idProducto) {
        return repository.findById(idProducto)
                .map(producto -> Optional.of(mapper.productoEntityToProductoModel(producto)))
                .orElse(Optional.empty());
    }

    public void eliminaProducto(long idProducto){
        repository.deleteById(idProducto);
    }

    public void actualizaProducto(Producto producto, long id){
        Optional<modulo1fase3.proyecto.entities.Producto> actual = repository.findById(id);

        if (!actual.isPresent()) {
            throw new ElementNotFoundException();
        }

        modulo1fase3.proyecto.entities.Producto producto1 = actual.get();
        mapper.productoModelToProductoEntity(producto1, producto);
        repository.save(producto1);
    }

    public long cuenteProductos(){
        return repository.count();
    }
}