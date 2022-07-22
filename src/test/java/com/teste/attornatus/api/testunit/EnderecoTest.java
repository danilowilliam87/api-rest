package com.teste.attornatus.api.testunit;

import com.teste.attornatus.api.domain.Endereco;
import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.exception.ServiceException;
import com.teste.attornatus.api.service.EnderecoService;
import com.teste.attornatus.api.service.PessoaService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class EnderecoTest {

    Endereco endereco;
    Endereco endereco2;

    Endereco endereco3;

    Endereco endereco4;
    Pessoa pessoa;

    @Autowired
    EnderecoService service;

    @Autowired
    PessoaService pessoaService;

    @BeforeAll
     void load(){

        pessoa = new Pessoa();
        pessoa.setNome("Joao de Jesus");
        pessoa.setDataNascimento(LocalDate.of(1989, 2, 10));
        pessoaService.save(pessoa);

        endereco3 = new Endereco();
        endereco2 = new Endereco();
        endereco = new Endereco();
        endereco4 = new Endereco();
        endereco.setLogradouro("Rua Jardins Brasil");
        endereco.setCep("43020-020");
        endereco.setCidade("Salvador");
        endereco.setPrincipal("SIM");
        endereco.setPessoa(pessoa);
        endereco.setNumero(10);

        endereco3.setLogradouro("");
        endereco3.setCidade("");
        endereco3.setCep("");
        endereco3.setNumero(100);
        endereco3.setPessoa(pessoa);

        endereco4.setLogradouro("Rua America");
        endereco4.setPrincipal("SIM");
        endereco4.setCidade("25487-555");
        endereco4.setCidade("Rio de Janeiro");
        endereco4.setNumero(10);
        endereco4.setPessoa(pessoa);
        endereco4.setId(10L);

    }


    @Test
     void saveEnderecoTest(){
         service.save(endereco);
         Endereco busca = new Endereco();
         busca = service.findById(1L);
        Assertions.assertEquals("Rua Jardins Brasil", busca.getLogradouro());
    }

    @Test
    void saveEnderecoServiceException(){
        Assertions.assertThrows(ServiceException.class, ()->{
            service.save(endereco2);
        });
    }

    @Test
    void saveEnderecoWithBlankData(){
        Assertions.assertThrows(ServiceException.class, ()->{
            service.save(endereco3);
        });
    }

    @Test
    void saveEnderecoPrincipal(){

        service.save(endereco);
        Endereco end = new Endereco();
        end.setLogradouro("Rua das Gaivotas");
        end.setPrincipal("SIM");
        end.setCep("43240-200");
        end.setCidade("Sao Paulo");
        end.setPessoa(pessoa);
        end.setNumero(1000);
        service.save(end);

        Long id = 1L;
        Endereco busca = service.findById(id);

        Assertions.assertEquals("NAO", busca.getPrincipal());


    }

    @Test
    void saveEnderecoNoPrincipal(){

        service.save(endereco);
        Endereco end = new Endereco();
        end.setLogradouro("Rua das Gaivotas");
        end.setPrincipal("NAO");
        end.setCep("43240-200");
        end.setCidade("Sao Paulo");
        end.setPessoa(pessoa);
        end.setNumero(1000);
        service.save(end);
        Long id = 1L;
        Endereco busca = service.findById(id);
        Assertions.assertEquals("SIM", busca.getPrincipal());
    }

    @Test
    void findAllEnderecoByPessoaTeste(){
        service.save(endereco);
        Endereco end = new Endereco();
        end.setLogradouro("Rua das Gaivotas");
        end.setPrincipal("NAO");
        end.setCep("43240-200");
        end.setCidade("Sao Paulo");
        end.setPessoa(pessoa);
        end.setNumero(1000);
        service.save(end);
        List<Endereco> lista = service.findAllByPessoa(1L);
        Assertions.assertEquals(5, lista.size());

    }

    @Test
    void findAllEndrecoByPessoaTeste2(){
        service.save(endereco);
        Endereco end = new Endereco();
        end.setLogradouro("Rua das Gaivotas");
        end.setPrincipal("NAO");
        end.setCep("43240-200");
        end.setCidade("Sao Paulo");
        end.setPessoa(pessoa);
        end.setNumero(1000);
        service.save(end);
        List<Endereco> lista = service.findAllByPessoa(1L);
        Assertions.assertTrue(lista.contains(end));

    }

    @Test
    void findAllEnderecoByPessoaTeste3(){
        service.save(endereco);
        Endereco end = new Endereco();
        end.setLogradouro("Rua das Gaivotas");
        end.setPrincipal("NAO");
        end.setCep("43240-200");
        end.setCidade("Sao Paulo");
        end.setPessoa(pessoa);
        end.setNumero(1000);
        service.save(end);
        List<Endereco> lista = service.findAllByPessoa(1L);
        Assertions.assertFalse(lista.contains(endereco4));

    }



}
