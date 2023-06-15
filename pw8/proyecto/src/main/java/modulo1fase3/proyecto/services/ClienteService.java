package modulo1fase3.proyecto.services;

import lombok.RequiredArgsConstructor;
import modulo1fase3.proyecto.controllers.mappers.ClienteMapper;
import modulo1fase3.proyecto.model.Cliente;
import modulo1fase3.proyecto.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public Cliente guardaCliente(Cliente cliente) {
        return mapper.clienteEntityToClienteModel(
                repository.save(mapper.clienteModelToClienteEntity(cliente))
        );
    }

    public List<Cliente> obtenClientes(){
        return repository.findAll().stream()
                .map(cliente -> mapper.clienteEntityToClienteModel(cliente))
                .collect(Collectors.toList());
    }

    public Optional<Cliente> obtenCliente(long idCliente) {
        return repository.findById(idCliente)
                .map(cliente -> Optional.of(mapper.clienteEntityToClienteModel(cliente)))
                .orElse(Optional.empty());
    }

    public void eliminaCliente(long idcliente){
        repository.deleteById(idcliente);
    }

    public void actualizaCliente(Cliente cliente, long id){
        Optional<modulo1fase3.proyecto.entities.Cliente> actual = repository.findById(id);

       /* if (!actual.isPresent()) {
            throw new GuestNotFoundException();
        }*/

        modulo1fase3.proyecto.entities.Cliente cliente1 = actual.get();
        mapper.clienteModelToClienteEntity(cliente1, cliente);
        repository.save(cliente1);
    }


    public long cuenteClientes(){
        return repository.count();
    }
}
