package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.mappers.ClienteMapper;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteDataProvider {

    private final ClienteRepository repository;

    public Cliente salvar(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(cliente);

        try {
            clienteEntity = repository.save(clienteEntity);
        } catch (Exception ex) {
            throw new BancoDeDadosException("Erro ao salvar Cliente");
        }

        return ClienteMapper.paraCliente(clienteEntity);
    }

    public List<Cliente> consultarTodos() {
        try {
            List<ClienteEntity> clienteEntities = repository.findAll()
                    .stream()
                    .filter(ClienteEntity -> !ClienteEntity.getInativo())
                    .toList();
            
            return ClienteMapper.paraClientes(clienteEntities);
        } catch (Exception ex) {
            throw new BancoDeDadosException("Erro ao buscar todos os Clientes");
        }
    }

    public Optional<Cliente> consultarPorId(Long id) {

        Optional<ClienteEntity> clienteEntity;

        try {
            clienteEntity = repository.findById(id);
        } catch (Exception ex) {
            throw new BancoDeDadosException("Erro ao consultar Cliente por Id.");
        }

        return clienteEntity.isEmpty() ? Optional.empty() : Optional.of(ClienteMapper.paraCliente(clienteEntity.get()));
    }
}
