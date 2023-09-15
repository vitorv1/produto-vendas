package com.example.produtovendas.infra.dataproviders;

import com.example.produtovendas.builders.Builders;
import com.example.produtovendas.domain.Cliente;
import com.example.produtovendas.domain.Produto;
import com.example.produtovendas.domain.Venda;
import com.example.produtovendas.infra.entities.ClienteEntity;
import com.example.produtovendas.infra.entities.ProdutoEntity;
import com.example.produtovendas.infra.entities.VendaEntity;
import com.example.produtovendas.infra.exceptions.BancoDeDadosException;
import com.example.produtovendas.infra.mappers.ProdutoMapper;
import com.example.produtovendas.infra.mappers.VendaMapper;
import com.example.produtovendas.infra.repositories.VendaRepository;
<<<<<<< HEAD
import com.example.produtovendas.validators.Validators;
=======
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.produtovendas.validators.Validators.validaVendaDomain;
import static org.mockito.ArgumentMatchers.any;

class VendaDataProviderTest {

    @Autowired
    private VendaDataProvider vendaDataProvider;

    @Mock
    private VendaRepository repository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.vendaDataProvider = new VendaDataProvider(repository);
    }

    @Test
    void testeMetodoSalvar(){
        Venda venda = Builders.builderVendaDomain().get(0);
        venda.setId(null);

        Mockito.when(repository.save(any())).thenReturn(VendaMapper.paraEntity(venda));

        Venda vendaTeste = vendaDataProvider.salvar(venda);
        validaVendaDomain(vendaTeste, null);
    }

    @Test
    void testaMetodobuscaPorId(){
<<<<<<< HEAD
        Long id = 2L;
        Optional<VendaEntity> vendaEntity = Builders.builderVendaOptional().get(0);
        Mockito.when(repository.findById(any())).thenReturn(vendaEntity);

        Optional<Venda> vendaTeste = vendaDataProvider.buscarPorId(id);
        vendaTeste.ifPresent(venda -> validaVendaDomain(vendaTeste.get(), null));
=======
        Long id = 1L;
        Long idCliente = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "123456789-11";
        String email = "vitorhvvieira@gmail.com";
        String numeroTelefone = "vitorhvvieira@gmail.com";

        Long idProduto1 = 2L;
        String nomeProduto1 = "Tenis";
        String marcaProduto1 = "Vans";
        double valor1 = 300;

        Long idProduto2 = 4L;
        String nomeProduto2 = "Camiseta";
        String marcaProduto2 = "High";
        double valor2 = 400;

        ClienteEntity clienteEntity = new ClienteEntity(idCliente, nome, inativo, cpf, email, numeroTelefone);
        ProdutoEntity produtoEntity1 = new ProdutoEntity(idProduto1, nomeProduto1, inativo,marcaProduto1,valor1);
        ProdutoEntity produtoEntity2 = new ProdutoEntity(idProduto2, nomeProduto2, inativo, marcaProduto2, valor2);

        List<ProdutoEntity> produtoEntityList = new ArrayList<>();
        produtoEntityList.add(produtoEntity1);
        produtoEntityList.add(produtoEntity2);

        Optional<VendaEntity> vendaEntity = Optional.of(new VendaEntity(1L, clienteEntity, inativo ,0 ,  0, produtoEntityList, LocalDate.now()));

        Mockito.when(repository.findById(any())).thenReturn(vendaEntity);

        Optional<Venda> vendaTeste = vendaDataProvider.buscarPorId(id);
        vendaTeste.ifPresent(venda -> {
            Assertions.assertEquals(id ,vendaTeste.get().getId());
            Assertions.assertEquals(clienteEntity.getId(), vendaTeste.get().getCliente().getId());
            Assertions.assertEquals(clienteEntity.getNome(), vendaTeste.get().getCliente().getNome());
            Assertions.assertEquals(clienteEntity.isInativo(), vendaTeste.get().getCliente().isInativo());
            Assertions.assertEquals(clienteEntity.getCpf(), vendaTeste.get().getCliente().getCpf());
            Assertions.assertEquals(clienteEntity.getEmail(), vendaTeste.get().getCliente().getEmail());
            Assertions.assertEquals(clienteEntity.getNumeroTelefone(), vendaTeste.get().getCliente().getNumeroTelefone());
            Assertions.assertEquals(idCliente, vendaTeste.get().getIdCliente());
            Assertions.assertEquals(0, vendaTeste.get().getValor());
            Assertions.assertEquals(inativo, vendaTeste.get().isInativo());
            Assertions.assertEquals(0, vendaTeste.get().getDesconto());
            Assertions.assertEquals(produtoEntity1.getId(), vendaTeste.get().getListaProdutos().get(0).getId());
            Assertions.assertEquals(produtoEntity1.getNome(), vendaTeste.get().getListaProdutos().get(0).getNome());
            Assertions.assertEquals(produtoEntity1.isInativo(), vendaTeste.get().getListaProdutos().get(0).isInativo());
            Assertions.assertEquals(produtoEntity1.getMarca(), vendaTeste.get().getListaProdutos().get(0).getMarca());
            Assertions.assertEquals(produtoEntity1.getValor(), vendaTeste.get().getListaProdutos().get(0).getValor());
            Assertions.assertEquals(produtoEntity2.getId(), vendaTeste.get().getListaProdutos().get(1).getId());
            Assertions.assertEquals(produtoEntity2.getNome(), vendaTeste.get().getListaProdutos().get(1).getNome());
            Assertions.assertEquals(produtoEntity2.isInativo(), vendaTeste.get().getListaProdutos().get(1).isInativo());
            Assertions.assertEquals(produtoEntity2.getMarca(), vendaTeste.get().getListaProdutos().get(1).getMarca());
            Assertions.assertEquals(produtoEntity2.getValor(), vendaTeste.get().getListaProdutos().get(1).getValor());
            Assertions.assertEquals(LocalDate.now(), vendaTeste.get().getDataVenda());
        });

>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }

    @Test
    void testeMetodoBuscarTodos(){
<<<<<<< HEAD
        List<VendaEntity> vendaEntities = Builders.builderVenda();
        Mockito.when(repository.findAll()).thenReturn(vendaEntities);

        List<Venda> vendaListTeste = vendaDataProvider.buscarTodos();
        validaVendaDomain(vendaListTeste.get(0), vendaListTeste.get(1));
=======
        Long id1 = 1L;
        Long id2 = 2L;
        Long idCliente = 1L;
        String nome = "Vitor";
        boolean inativo = false;
        String cpf = "123456789-11";
        String email = "vitorhvvieira@gmail.com";
        String numeroTelefone = "vitorhvvieira@gmail.com";

        Long idProduto1 = 2L;
        String nomeProduto1 = "Tenis";
        String marcaProduto1 = "Vans";
        double valor1 = 300;

        Long idProduto2 = 4L;
        String nomeProduto2 = "Camiseta";
        String marcaProduto2 = "High";
        double valor2 = 400;

        ClienteEntity clienteEntity = new ClienteEntity(idCliente, nome, inativo, cpf, email, numeroTelefone);
        ProdutoEntity produtoEntity1 = new ProdutoEntity(idProduto1, nomeProduto1, inativo,marcaProduto1,valor1);
        ProdutoEntity produtoEntity2 = new ProdutoEntity(idProduto2, nomeProduto2, inativo, marcaProduto2, valor2);

        List<ProdutoEntity> produtoEntityList = new ArrayList<>();
        produtoEntityList.add(produtoEntity1);
        produtoEntityList.add(produtoEntity2);

        VendaEntity vendaEntity1 = new VendaEntity(id1, clienteEntity, inativo ,0 ,  0, produtoEntityList, LocalDate.now());
        VendaEntity vendaEntity2 = new VendaEntity(id2, clienteEntity, inativo ,0 ,  0, produtoEntityList, LocalDate.now());

        List<VendaEntity> vendaEntityList = new ArrayList<>();
        List<Produto> produtoList = ProdutoMapper.paraProdutos(produtoEntityList);

        vendaEntityList.add(vendaEntity1);
        vendaEntityList.add(vendaEntity2);

        Mockito.when(repository.findAll()).thenReturn(vendaEntityList);

        List<Venda> vendaListTeste = vendaDataProvider.buscarTodos();

        Assertions.assertEquals(id1, vendaListTeste.get(0).getId());
        Assertions.assertEquals(clienteEntity.getNome(), vendaListTeste.get(0).getCliente().getNome());
        Assertions.assertEquals(clienteEntity.getId(), vendaListTeste.get(0).getCliente().getId());
        Assertions.assertEquals(clienteEntity.isInativo(), vendaListTeste.get(0).getCliente().isInativo());
        Assertions.assertEquals(clienteEntity.getCpf(), vendaListTeste.get(0).getCliente().getCpf());
        Assertions.assertEquals(clienteEntity.getEmail(), vendaListTeste.get(0).getCliente().getEmail());
        Assertions.assertEquals(clienteEntity.getNumeroTelefone(), vendaListTeste.get(0).getCliente().getNumeroTelefone());
        Assertions.assertEquals(inativo, vendaListTeste.get(0).isInativo());
        Assertions.assertEquals(0, vendaListTeste.get(0).getValor());
        Assertions.assertEquals(0, vendaListTeste.get(0).getDesconto());
        Assertions.assertEquals(produtoList.get(0).getId(), vendaListTeste.get(0).getListaProdutos().get(0).getId());
        Assertions.assertEquals(produtoList.get(0).getNome(), vendaListTeste.get(0).getListaProdutos().get(0).getNome());
        Assertions.assertEquals(produtoList.get(0).getMarca(), vendaListTeste.get(0).getListaProdutos().get(0).getMarca());
        Assertions.assertEquals(produtoList.get(0).getValor(), vendaListTeste.get(0).getListaProdutos().get(0).getValor());
        Assertions.assertEquals(produtoList.get(0).isInativo(), vendaListTeste.get(0).getListaProdutos().get(0).isInativo());
        Assertions.assertEquals(produtoList.get(1).getId(), vendaListTeste.get(0).getListaProdutos().get(1).getId());
        Assertions.assertEquals(produtoList.get(1).getNome(), vendaListTeste.get(0).getListaProdutos().get(1).getNome());
        Assertions.assertEquals(produtoList.get(1).getMarca(), vendaListTeste.get(0).getListaProdutos().get(1).getMarca());
        Assertions.assertEquals(produtoList.get(1).getValor(), vendaListTeste.get(0).getListaProdutos().get(1).getValor());
        Assertions.assertEquals(produtoList.get(1).isInativo(), vendaListTeste.get(0).getListaProdutos().get(1).isInativo());
        Assertions.assertEquals(LocalDate.now(), vendaListTeste.get(0).getDataVenda());
        Assertions.assertEquals(id2, vendaListTeste.get(1).getId());
        Assertions.assertEquals(clienteEntity.getNome(), vendaListTeste.get(1).getCliente().getNome());
        Assertions.assertEquals(clienteEntity.getId(), vendaListTeste.get(1).getCliente().getId());
        Assertions.assertEquals(clienteEntity.isInativo(), vendaListTeste.get(1).getCliente().isInativo());
        Assertions.assertEquals(clienteEntity.getCpf(), vendaListTeste.get(1).getCliente().getCpf());
        Assertions.assertEquals(clienteEntity.getEmail(), vendaListTeste.get(1).getCliente().getEmail());
        Assertions.assertEquals(clienteEntity.getNumeroTelefone(), vendaListTeste.get(1).getCliente().getNumeroTelefone());
        Assertions.assertEquals(inativo, vendaListTeste.get(1).isInativo());
        Assertions.assertEquals(0, vendaListTeste.get(1).getValor());
        Assertions.assertEquals(0, vendaListTeste.get(1).getDesconto());
        Assertions.assertEquals(produtoList.get(0).getId(), vendaListTeste.get(1).getListaProdutos().get(0).getId());
        Assertions.assertEquals(produtoList.get(0).getNome(), vendaListTeste.get(1).getListaProdutos().get(0).getNome());
        Assertions.assertEquals(produtoList.get(0).getMarca(), vendaListTeste.get(1).getListaProdutos().get(0).getMarca());
        Assertions.assertEquals(produtoList.get(0).getValor(), vendaListTeste.get(1).getListaProdutos().get(0).getValor());
        Assertions.assertEquals(produtoList.get(0).isInativo(), vendaListTeste.get(1).getListaProdutos().get(0).isInativo());
        Assertions.assertEquals(produtoList.get(1).getId(), vendaListTeste.get(1).getListaProdutos().get(1).getId());
        Assertions.assertEquals(produtoList.get(1).getNome(), vendaListTeste.get(1).getListaProdutos().get(1).getNome());
        Assertions.assertEquals(produtoList.get(1).getMarca(), vendaListTeste.get(1).getListaProdutos().get(1).getMarca());
        Assertions.assertEquals(produtoList.get(1).getValor(), vendaListTeste.get(1).getListaProdutos().get(1).getValor());
        Assertions.assertEquals(produtoList.get(1).isInativo(), vendaListTeste.get(1).getListaProdutos().get(1).isInativo());
        Assertions.assertEquals(LocalDate.now(), vendaListTeste.get(1).getDataVenda());
>>>>>>> 133e1bf57b292e1800f28c46e617fd4b39f99c02
    }

    @Test
    void testaSeMetodoSalvarEstaLancandoExecption(){
        Mockito.when(repository.save(any())).thenThrow(RuntimeException.class);
        List<Produto> produtoList = new ArrayList<>();
        BancoDeDadosException exceptionTeste = Assertions.assertThrows(BancoDeDadosException.class, () -> vendaDataProvider.salvar(
                new Venda(1L, new Cliente(2L, "Vitor", false, "123456789-77", "vivi@gmail.com", "124578-9856"),
                        2L, 0, false, 0, produtoList, LocalDate.now())));
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
    }
}