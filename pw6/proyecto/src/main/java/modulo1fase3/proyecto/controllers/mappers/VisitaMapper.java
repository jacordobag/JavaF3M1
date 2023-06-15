package modulo1fase3.proyecto.controllers.mappers;

import modulo1fase3.proyecto.entities.Visita;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitaMapper {
    Visita visitaModelToVisitaEntity(modulo1fase3.proyecto.model.Visita visitaModel);

    modulo1fase3.proyecto.model.Visita visitaEntityToVisitaModel(Visita visita);
}