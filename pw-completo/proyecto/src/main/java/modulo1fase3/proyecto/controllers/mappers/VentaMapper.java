package modulo1fase3.proyecto.controllers.mappers;

import modulo1fase3.proyecto.entities.Etapa;
import modulo1fase3.proyecto.entities.Venta;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VentaMapper {
    Venta ventaModelToVentaEntity(modulo1fase3.proyecto.model.Venta ventaModel);

    modulo1fase3.proyecto.model.Venta ventaEntityToVentaModel(Venta venta);

    @Mapping(target = "ventaId", ignore = true)
    void ventaModelToVentaEntity(@MappingTarget Venta entity, modulo1fase3.proyecto.model.Venta data);
}
