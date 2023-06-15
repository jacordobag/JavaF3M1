package modulo1fase3.proyecto.controllers.mappers;

import modulo1fase3.proyecto.entities.Venta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VentaMapper {
    Venta ventaModelToVentaEntity(modulo1fase3.proyecto.model.Venta ventaModel);

    modulo1fase3.proyecto.model.Venta ventaEntityToVentaModel(Venta venta);
}
