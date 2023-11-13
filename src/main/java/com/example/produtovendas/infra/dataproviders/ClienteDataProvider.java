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

    private static final String MENSAGEM_ERRO_CADASTRO_CLIENTE = "Erro ao salvar Cliente";
    private static final String MENSAGEM_ERRO_CONSULTA_TODOS_CLIENTES = "Erro ao buscar todos os Clientes";
    private static final String MENSAGEM_ERRO_CONSULTA_CPF_CLIENTE = "Erro ao buscar todos os Clientes";
    private static final String MENSAGEM_ERRO_CONSULTA_ID_CLIENTE = "Erro ao consultar Cliente por Id";

    public Cliente salvar(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteMapper.paraEntity(cliente);

        try {
            clienteEntity = repository.save(clienteEntity);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CADASTRO_CLIENTE, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_CADASTRO_CLIENTE);
        }

        return ClienteMapper.paraDomain(clienteEntity);
    }

    public List<Cliente> consultarTodos() {
        try {
            return ClienteMapper.paraDomains(repository.findAll());
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTA_TODOS_CLIENTES, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_CONSULTA_TODOS_CLIENTES);
        }
    }

    public Optional<Cliente> consultarPorCpf(String cpf) {
        try {
            Optional<ClienteEntity> cliente = repository.findByCpf(cpf);
            return cliente.isPresent() ? Optional.of(ClienteMapper.paraDomain(cliente.get())) : Optional.empty();
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTA_CPF_CLIENTE, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_CONSULTA_CPF_CLIENTE);
        }
    }

    public Optional<Cliente> consultarPorId(Long id) {

        Optional<ClienteEntity> clienteEntity;

        try {
            clienteEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTA_ID_CLIENTE, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_CONSULTA_ID_CLIENTE);
        }

        return clienteEntity.isEmpty() ? Optional.empty() : Optional.of(ClienteMapper.paraDomain(clienteEntity.get()));
    }
}
