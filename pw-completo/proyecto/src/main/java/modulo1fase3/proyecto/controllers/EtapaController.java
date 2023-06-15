package modulo1fase3.proyecto.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import modulo1fase3.proyecto.model.Cliente;
import modulo1fase3.proyecto.model.Etapa;
import modulo1fase3.proyecto.model.Producto;
import modulo1fase3.proyecto.services.ClienteService;
import modulo1fase3.proyecto.services.EtapaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/etapa")
@RequiredArgsConstructor
public class EtapaController {
    private final EtapaService etapaService;

    @GetMapping("/{etapaId}")
    public ResponseEntity<Etapa> getEtapa(@PathVariable Long etapaId){
        Optional<Etapa> etapaDB = etapaService.obtenEtapa(etapaId);

        return ResponseEntity.ok(etapaDB.get());

    }

    @GetMapping
    public ResponseEntity <List<Etapa>> getEtapas(){
        return ResponseEntity.ok(etapaService.obtenEtapas());

    }

    @PostMapping
    public ResponseEntity<Void> creaEtapa(@Valid @RequestBody Etapa etapa){
        Etapa etapaNueva = etapaService.guardaEtapa(etapa);

        return ResponseEntity.created(URI.create(String.valueOf(etapaNueva.getEtapaId()))).build();

    }

    @PutMapping("/{etapaId}")
    public ResponseEntity<Void> actualizaEtapa(@PathVariable("etapaId") long etapaId, @RequestBody @Valid Etapa etapa){
        etapaService.actualizaEtapa(etapa, etapaId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{etapaId}")
    public ResponseEntity<Void> eliminaEtapa(@PathVariable Long etapaId){
        etapaService.eliminaEtapa(etapaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
