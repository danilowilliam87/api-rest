package com.teste.attornatus.api.testeunitario;

import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.repository.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
public class PessoaTest {

    @Autowired
    PessoaRepository repository;


    @Test
    void savePessoa(){
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Dipsi");
        pessoa1.setDataNascimento(LocalDate.now());
        repository.save(pessoa1);
        Optional<Pessoa> pessoa2 = repository.findById(4L);
        Assertions.assertEquals("Dipsi", pessoa2.get().getNome());
    }

    @Test
    void buscaPessoa(){
        Optional<Pessoa> pessoa2 = repository.findById(1L);
        Assertions.assertEquals("Divi", pessoa2.get().getNome());
    }

}
