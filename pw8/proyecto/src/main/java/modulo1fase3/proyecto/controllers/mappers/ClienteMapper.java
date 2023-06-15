package modulo1fase3.proyecto.controllers.mappers;

import modulo1fase3.proyecto.entities.Cliente;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper {
    Cliente clienteModelToClienteEntity(modulo1fase3.proyecto.model.Cliente clienteModel);

    modulo1fase3.proyecto.model.Cliente clienteEntityToClienteModel(Cliente cliente);

    @Mapping(target = "id", ignore = true)
    void clienteModelToClienteEntity(@MappingTarget Cliente entity, modulo1fase3.proyecto.model.Cliente data);


}
