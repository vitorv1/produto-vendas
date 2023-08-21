package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.mappers.ClienteMapper;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.repositories.ClienteRepository;
import com.example.produtovendas.infra.validacoes.ClienteValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ClienteDataProvider {


    private final ClienteRepository repository;

    @Autowired
    public ClienteDataProvider(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        List<ClienteEntity> clienteEntities;
        try {
            clienteEntities = repository.findAll();
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new BancoDeDadosException("Erro na consulta de todos os clientes para validção");
        }
        ClienteValidation.validaCliente(ClienteMapper.paraClientes(clienteEntities), cliente);
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(cliente);

        try {
            clienteEntity = repository.save(clienteEntity);
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new BancoDeDadosException("Erro ao salvar Cliente");
        }

        return ClienteMapper.paraCliente(clienteEntity);
    }

    public List<Cliente> consultarTodos() {
        try {
            return ClienteMapper.paraClientes(repository.findAll());
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new BancoDeDadosException("Erro ao buscar todos os Clientes");
        }
    }

    public Optional<Cliente> consultarPorId(Long id) {

        Optional<ClienteEntity> clienteEntity;

        try {
            clienteEntity = repository.findById(id);
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new BancoDeDadosException("Erro ao consultar Cliente por Id.");
        }

        return clienteEntity.isEmpty() ? Optional.empty() : Optional.of(ClienteMapper.paraCliente(clienteEntity.get()));
    }

    public Cliente aterarCliente(Long id, Cliente clienteDto){
        Optional<Cliente> cliente = consultarPorId(id);
        if(cliente.isPresent()){
            cliente.get().atualizarDados(clienteDto);
            ClienteEntity clienteEntity = repository.save(ClienteMapper.paraEntity(cliente.get()));
            return ClienteMapper.paraCliente(clienteEntity);
        }else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public void deletarCliente(Long id){
        Optional<Cliente> cliente = consultarPorId(id);
        if(cliente.isPresent()){
            cliente.get().inativar();
            ClienteEntity clienteEntityTeste = ClienteMapper.paraEntity(cliente.get());
            repository.save(clienteEntityTeste);
        }else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }
}
