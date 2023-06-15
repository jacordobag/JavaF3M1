package modulo1fase3.proyecto.controllers.mappers;

import modulo1fase3.proyecto.entities.Etapa;
import modulo1fase3.proyecto.entities.Visita;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VisitaMapper {
    Visita visitaModelToVisitaEntity(modulo1fase3.proyecto.model.Visita visitaModel);

    modulo1fase3.proyecto.model.Visita visitaEntityToVisitaModel(Visita visita);

    @Mapping(target = "id", ignore = true)
    void visitaModelToVisitaEntity(@MappingTarget Visita entity, modulo1fase3.proyecto.model.Visita data);
}