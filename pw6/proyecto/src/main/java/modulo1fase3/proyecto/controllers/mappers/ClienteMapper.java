package modulo1fase3.proyecto.controllers.mappers;

import modulo1fase3.proyecto.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente clienteModelToClienteEntity(modulo1fase3.proyecto.model.Cliente clienteModel);

    modulo1fase3.proyecto.model.Cliente clienteEntityToClienteModel(Cliente cliente);
}
