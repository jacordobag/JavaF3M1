package modulo1fase3.proyecto.controllers.mappers;

import modulo1fase3.proyecto.entities.Etapa;
import modulo1fase3.proyecto.entities.Producto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductoMapper {
    Producto productoModelToProductoEntity(modulo1fase3.proyecto.model.Producto productoModel);

    modulo1fase3.proyecto.model.Producto productoEntityToProductoModel(Producto producto);

    @Mapping(target = "id", ignore = true)
    void productoModelToProductoEntity(@MappingTarget Producto entity, modulo1fase3.proyecto.model.Producto data);
}
