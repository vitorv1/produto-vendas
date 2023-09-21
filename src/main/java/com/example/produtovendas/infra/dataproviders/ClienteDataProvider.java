package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.ClienteMapper;
import com.example.produtovendas.infra.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClienteDataProvider {

    private final ClienteRepository repository;

    public Cliente salvar(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(cliente);

        try {
            clienteEntity = repository.save(clienteEntity);
        } catch (Exception ex) {
            log.error("Erro ao salvar Cliente", ex);
            throw new BancoDeDadosException("Erro ao salvar Cliente");
        }

        return ClienteMapper.paraCliente(clienteEntity);
    }

    public List<Cliente> consultarTodos() {
        try {
            return ClienteMapper.paraClientes(repository.findAll());
        } catch (Exception ex) {
            log.error("Erro ao buscar todos os Clientes", ex);
            throw new BancoDeDadosException("Erro ao buscar todos os Clientes");
        }
    }

    public Optional<Cliente> consultarPorCpf(String cpf) {
        try {
            Optional<ClienteEntity> cliente = repository.findByCpf(cpf);
            return cliente.isPresent() ? Optional.of(ClienteMapper.paraCliente(cliente.get())) : Optional.empty();
        } catch (Exception ex) {
            log.error("Erro ao buscar todos os Clientes", ex);
            throw new BancoDeDadosException("Erro ao buscar todos os Clientes");
        }
    }

    public Optional<Cliente> consultarPorId(Long id) {

        Optional<ClienteEntity> clienteEntity;

        try {
            clienteEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error("Erro ao consultar Cliente por Id", ex);
            throw new BancoDeDadosException("Erro ao consultar Cliente por Id.");
        }

        return clienteEntity.isEmpty() ? Optional.empty() : Optional.of(ClienteMapper.paraCliente(clienteEntity.get()));
    }
}
