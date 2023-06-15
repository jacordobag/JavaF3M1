package modulo1fase3.proyecto.controllers.mappers;

import modulo1fase3.proyecto.entities.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    Producto productoModelToProductoEntity(modulo1fase3.proyecto.model.Producto productoModel);

    modulo1fase3.proyecto.model.Producto productoEntityToProductoModel(Producto producto);
}
