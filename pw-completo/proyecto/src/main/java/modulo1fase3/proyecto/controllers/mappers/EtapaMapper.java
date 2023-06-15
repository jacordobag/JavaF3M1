package modulo1fase3.proyecto.controllers.mappers;

import modulo1fase3.proyecto.entities.Cliente;
import modulo1fase3.proyecto.entities.Etapa;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EtapaMapper {
    Etapa etapaModelToEtapaEntity(modulo1fase3.proyecto.model.Etapa etapaModel);

    modulo1fase3.proyecto.model.Etapa etapaEntityToEtapaModel(Etapa etapa);

    @Mapping(target = "etapaId", ignore = true)
    void etapaModelToEtapaEntity(@MappingTarget Etapa entity, modulo1fase3.proyecto.model.Etapa data);
}
