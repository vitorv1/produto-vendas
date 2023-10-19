package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.VendaEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.VendaMapper;
import com.example.produtovendas.infra.repositories.VendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class VendaDataProvider {

    private final VendaRepository repositoryVenda;

    private static final String MENSAGEM_ERRO_SALVAR_VENDA = "Erro ao salvar venda";
    private static final String MENSAGEM_ERRO_BUSCA_ID_VENDA = "Erro ao consultar por id no banco de dados";
    private static final String MENSAGEM_ERRO_BUSCA_TODOS_VENDAS = "Erro ao consultar todos no banco de dados";

    public Venda salvar(Venda venda) {
        VendaEntity vendaEntity = VendaMapper.paraEntity(venda);
        try {
            vendaEntity = repositoryVenda.save(vendaEntity);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR_VENDA, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_SALVAR_VENDA);
        }
        return VendaMapper.paraDomain(vendaEntity);
    }

    public Optional<Venda> buscarPorId(Long id) {
        Optional<VendaEntity> vendaEntity;
        try {
            vendaEntity = repositoryVenda.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_BUSCA_ID_VENDA, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_BUSCA_ID_VENDA);
        }
        return vendaEntity.isEmpty() ? Optional.empty() : Optional.of(VendaMapper.paraDomain(vendaEntity.get()));
    }

    public List<Venda> buscarTodos() {
        try {
            return VendaMapper.paraDomains(repositoryVenda.findAll());
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_BUSCA_TODOS_VENDAS, ex);
            throw new BancoDeDadosException(MENSAGEM_ERRO_BUSCA_TODOS_VENDAS);
        }
    }
}
