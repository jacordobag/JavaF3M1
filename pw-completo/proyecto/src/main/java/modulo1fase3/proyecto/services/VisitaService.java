package modulo1fase3.proyecto.services;

import lombok.RequiredArgsConstructor;
import modulo1fase3.proyecto.controllers.mappers.VisitaMapper;
import modulo1fase3.proyecto.exceptions.ElementNotFoundException;
import modulo1fase3.proyecto.model.Visita;
import modulo1fase3.proyecto.repositories.VisitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitaService {
    private final VisitaRepository repository;
    private final VisitaMapper mapper;

    public Visita guardaVisita(Visita visita) {
        return mapper.visitaEntityToVisitaModel(
                repository.save(mapper.visitaModelToVisitaEntity(visita))
        );
    }

    public List<Visita> obtenVisitas(){
        return repository.findAll().stream().map(visita -> mapper.visitaEntityToVisitaModel(visita)).collect(Collectors.toList());
    }

    public Optional<Visita> obtenVisita(long idVisita) {
        return repository.findById(idVisita)
                .map(Visita -> Optional.of(mapper.visitaEntityToVisitaModel(Visita)))
                .orElse(Optional.empty());
    }

    public void eliminaVisita(long idVisita){
        repository.deleteById(idVisita);
    }

    public void actualizaVisita(Visita visita, long id){
        Optional<modulo1fase3.proyecto.entities.Visita> actual = repository.findById(id);

        if (!actual.isPresent()) {
            throw new ElementNotFoundException();
        }

        modulo1fase3.proyecto.entities.Visita visita1 = actual.get();
        mapper.visitaModelToVisitaEntity(visita1, visita);
        repository.save(visita1);
    }

    public long cuenteVisitas(){
        return repository.count();
    }
}