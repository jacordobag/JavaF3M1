package modulo1fase3.proyecto.services;

import lombok.RequiredArgsConstructor;
import modulo1fase3.proyecto.controllers.mappers.VentaMapper;
import modulo1fase3.proyecto.exceptions.ElementNotFoundException;
import modulo1fase3.proyecto.model.Venta;
import modulo1fase3.proyecto.repositories.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaService {
    private final VentaRepository repository;
    private final VentaMapper mapper;

    public Venta guardaVenta(Venta Venta) {
        return mapper.ventaEntityToVentaModel(
                repository.save(mapper.ventaModelToVentaEntity(Venta))
        );
    }

    public List<Venta> obtenVentas(){
        return repository.findAll().stream().map(venta -> mapper.ventaEntityToVentaModel(venta)).collect(Collectors.toList());
    }

    public Optional<Venta> obtenVenta(long idVenta) {
        return repository.findById(idVenta)
                .map(venta -> Optional.of(mapper.ventaEntityToVentaModel(venta)))
                .orElse(Optional.empty());
    }

    public void eliminaVenta(long idVenta){
        repository.deleteById(idVenta);
    }

    public void actualizaVenta(Venta venta, long id){
        Optional<modulo1fase3.proyecto.entities.Venta> actual = repository.findById(id);

        if (!actual.isPresent()) {
            throw new ElementNotFoundException();
        }

        modulo1fase3.proyecto.entities.Venta venta1 = actual.get();
        mapper.ventaModelToVentaEntity(venta1, venta);
        repository.save(venta1);
    }

    public long cuenteVentas(){
        return repository.count();
    }
}
