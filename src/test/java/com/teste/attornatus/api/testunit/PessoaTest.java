package com.teste.attornatus.api.testunit;

import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.exception.ResourceNotFoundException;
import com.teste.attornatus.api.exception.ResultUniqueException;
import com.teste.attornatus.api.exception.ServiceException;
import com.teste.attornatus.api.repository.PessoaRepository;
import com.teste.attornatus.api.service.PessoaService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PessoaTest {

    @Autowired
    PessoaService service;

    @Autowired
    PessoaRepository repository;

    Pessoa pessoa;


    @BeforeEach
    void load(){
        pessoa = new Pessoa();
        pessoa.setNome("Mestre Yoda");
        pessoa.setDataNascimento(LocalDate.of(2000,10,10));
    }

    @AfterEach
    void finish(){
        repository.delete(pessoa);
    }

    @Test
    void savePessoa(){
        service.save(pessoa);
        Pessoa busca = service.findById(service.findByNome("Mestre Yoda").getId());
        Assertions.assertEquals(pessoa.getNome(), busca.getNome());
    }

    @Test
    void savePessoaServiceException(){
        Pessoa pessoa = new Pessoa();
        Assertions.assertThrows(ServiceException.class, ()->{
            service.save(pessoa);
        });
    }

    @Test
    void savePessoaException(){
        service.save(pessoa);
        Assertions.assertThrows(ResultUniqueException.class, ()->{
           service.save(pessoa);
        });
    }

    @Test
    void updateFullPessoa(){
        service.save(pessoa);
        Pessoa atualizar = new Pessoa();
        atualizar.setNome("Mestre Yoda do Amor Divino");
        atualizar.setDataNascimento(LocalDate.of(1900, 10, 10));
        Long id = service.findByNome("Mestre Yoda").getId();

        service.updateFull(id, atualizar);

        Pessoa busca = new Pessoa();
        busca = service.findById(id);

        Assertions.assertEquals("Mestre Yoda do Amor Divino", busca.getNome());
    }

    @Test
    void updateFullPessoaException(){
        service.save(pessoa);
        Pessoa atualizar = new Pessoa();
        atualizar.setNome("Mestre Yoda do Amor Divino");
        atualizar.setDataNascimento(LocalDate.of(1900, 10, 10));
        Long id = 1000L;

        Assertions.assertThrows(ResourceNotFoundException.class, ()->{
           service.updateFull(id, atualizar);
        });
    }

    @Test
    void updateFullPessoaServiceException(){
        service.save(pessoa);
        Pessoa atualizar = new Pessoa();
        Long id = 1L;

        Assertions.assertThrows(ServiceException.class, ()->{
            service.updateFull(id, atualizar);
        });
    }

    @Test
    void updateIncrementalPessoa(){
        service.save(pessoa);
        Long id = service.findByNome("Mestre Yoda").getId();
        Pessoa atualizar = new Pessoa();
        atualizar.setDataNascimento(LocalDate.of(2000, 1, 1));
        service.updateIncremental(id, atualizar);
        Pessoa busca = new Pessoa();
        busca = service.findById(id);

        Assertions.assertEquals(LocalDate.of(2000, 1, 1), busca.getDataNascimento());

    }


    @Test
    void updateIncrementalPessoa2(){
        service.save(pessoa);
        Long id = 1L;
        Pessoa atualizar = new Pessoa();
        atualizar.setNome("Mestree yoda do amor divino de jesus");
        service.updateIncremental(id, atualizar);
        Pessoa busca = new Pessoa();
        busca = service.findById(id);

        Assertions.assertEquals("Mestree yoda do amor divino de jesus", busca.getNome());

    }

    @Test
    void findByName(){
        service.save(pessoa);
        Pessoa busca = new Pessoa();
        busca = service.findByNome("Mestre Yoda");

        Assertions.assertEquals(LocalDate.of(2000,10,10), busca.getDataNascimento());
    }


    @Test
    void findAll(){
        service.save(pessoa);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Mestre Yoda dos santos");
        pessoa2.setDataNascimento(LocalDate.now());
        service.save(pessoa2);

        List<Pessoa> pessoas = new ArrayList<>();
        pessoas = service.findAll();

        Assertions.assertNotEquals(1, pessoas.size());
    }

}
