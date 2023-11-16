package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.VendaEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.VendaMapper;
import com.example.produtovendas.infra.repositories.VendaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static com.example.produtovendas.validators.Validators.validaVendaDomain;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class VendaDataProviderTest {


    /*@Mock
    private VendaRepository repository;

    @InjectMocks
    private VendaDataProvider vendaDataProvider;

    @Test
    void testeMetodoSalvar(){
        Venda venda = Builders.builderVendaDomain().get(0);
        venda.setId(null);

        Mockito.when(repository.save(any())).thenReturn(VendaMapper.paraEntity(venda));

        Venda vendaTeste = vendaDataProvider.salvar(venda);
        validaVendaDomain(vendaTeste, 0);
    }

    @Test
    void testaMetodobuscaPorId(){
        Long id = 2L;
        Optional<VendaEntity> vendaEntity = Builders.builderVendaOptional().get(0);
        Mockito.when(repository.findById(any())).thenReturn(vendaEntity);

        Optional<Venda> vendaTeste = vendaDataProvider.buscarPorId(id);
        vendaTeste.ifPresent(venda -> validaVendaDomain(vendaTeste.get(), 0));
    }

    @Test
    void testeMetodoBuscarTodos(){
        List<VendaEntity> vendaEntities = Builders.builderVendaEntity();
        Mockito.when(repository.findAll()).thenReturn(vendaEntities);

        List<Venda> vendaListTeste = vendaDataProvider.buscarTodos();
        validaVendaDomain(vendaListTeste.get(0), 0);
        validaVendaDomain(vendaListTeste.get(1), 1);
    }

    @Test
    void testaSeMetodoSalvarEstaLancandoExecption(){
        Mockito.when(repository.save(any())).thenThrow(RuntimeException.class);
        List<Produto> produtoList = new ArrayList<>();
        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, () -> vendaDataProvider.salvar(
                new Venda(1L, new Cliente(2L, "Vitor", false, "123456789-77", "vivi@gmail.com", "124578-9856"),
                        2L, new BigDecimal(0), false, 0, produtoList, LocalDate.now())));
        Assertions.assertEquals("Erro ao salvar venda", exceptionTeste.getMessage());
    }

    @Test
    void testaSeMetodoConsultarPorIdEstaLancandoException(){
        Mockito.when(repository.findById(any())).thenThrow(RuntimeException.class);
        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, () -> vendaDataProvider.buscarPorId(1L));
        Assertions.assertEquals("Erro ao consultar por id no banco de dados", exceptionTeste.getMessage());
    }

    @Test
    void testaSeMetodoConsultarTodosEstaLancandoException(){
        Mockito.when(repository.findAll()).thenThrow(RuntimeException.class);
        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, ()-> vendaDataProvider.buscarTodos());
        Assertions.assertEquals("Erro ao consultar todos no banco de dados", exceptionTeste.getMessage());
    }*/
}