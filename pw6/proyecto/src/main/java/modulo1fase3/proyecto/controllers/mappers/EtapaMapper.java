package modulo1fase3.proyecto.controllers.mappers;

import modulo1fase3.proyecto.entities.Etapa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EtapaMapper {
    Etapa etapaModelToEtapaEntity(modulo1fase3.proyecto.model.Etapa etapaModel);

    modulo1fase3.proyecto.model.Etapa etapaEntityToEtapaModel(Etapa etapa);
}
