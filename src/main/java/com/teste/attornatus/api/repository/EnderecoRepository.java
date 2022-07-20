package com.teste.attornatus.api.repository;

import com.teste.attornatus.api.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(value = "select e from endereco e where e.pessoa.id = :pessoaId")
    List<Endereco> findAllByIdPessoa(@Param("pessoaId") Long pessoaId);
}
