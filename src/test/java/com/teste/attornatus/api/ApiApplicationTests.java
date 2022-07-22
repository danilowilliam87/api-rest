package com.teste.attornatus.api;

import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.repository.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class ApiApplicationTests {

	@Autowired
	PessoaRepository repository;




}
