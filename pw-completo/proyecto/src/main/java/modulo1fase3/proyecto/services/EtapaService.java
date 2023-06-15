package modulo1fase3.proyecto.services;

import lombok.RequiredArgsConstructor;
import modulo1fase3.proyecto.controllers.mappers.EtapaMapper;
import modulo1fase3.proyecto.exceptions.ElementNotFoundException;
import modulo1fase3.proyecto.model.Etapa;
import modulo1fase3.proyecto.repositories.EtapaRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EtapaService {
    private final EtapaRepository repository;
    private final EtapaMapper mapper;

    public Etapa guardaEtapa(Etapa etapa) {
        return mapper.etapaEntityToEtapaModel(
                repository.save(mapper.etapaModelToEtapaEntity(etapa))
        );
    }

    public List<Etapa> obtenEtapas(){
        return repository.findAll().stream().map(etapa -> mapper.etapaEntityToEtapaModel(etapa)).collect(Collectors.toList());
    }

    public Optional<Etapa> obtenEtapa(long idEtapa) {
        return repository.findById(idEtapa)
                .map(Etapa -> Optional.of(mapper.etapaEntityToEtapaModel(Etapa)))
                .orElse(Optional.empty());
    }

    public void eliminaEtapa(long idEtapa){
        repository.deleteById(idEtapa);
    }

    public void actualizaEtapa(Etapa etapa, long id){
        Optional<modulo1fase3.proyecto.entities.Etapa> actual = repository.findById(id);

        if (!actual.isPresent()) {
            throw new ElementNotFoundException();
        }

        modulo1fase3.proyecto.entities.Etapa etapa1 = actual.get();
        mapper.etapaModelToEtapaEntity(etapa1, etapa);
        repository.save(etapa1);
    }

    public long cuenteEtapas(){
        return repository.count();
    }
}
