package com.teste.attornatus.api.service;

import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.error.ResourceNotFoundException;
import com.teste.attornatus.api.repository.PessoaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public Pessoa save(Pessoa pessoa){
        return repository.save(pessoa);
    }

    public Pessoa findById(Long id) {
      return repository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("objeto com id : " + id + " nao encontrado"));
    }

    public Pessoa findByNome(String nome)  {
        return repository.findByNome(nome)
                .orElseThrow(()-> new ResourceNotFoundException("objeto com nome : " + nome + " nao encontrado"));
    }

    public List<Pessoa> findAll(){
        return repository.findAll();
    }


    public Pessoa updateIncremental(Long id, Pessoa pessoa){
       Pessoa pessoaEncontrada = findById(id);
       pessoaEncontrada.setNome(Optional.ofNullable(pessoa.getNome()).orElse(pessoaEncontrada.getNome()));
       pessoaEncontrada.setDataNascimento(Optional.ofNullable(pessoa.getDataNascimento()).orElse(pessoaEncontrada.getDataNascimento()));
       return repository.save(pessoaEncontrada);
    }

    public Pessoa updateFull(Long id, Pessoa pessoa){
        Pessoa pessoaEncontrada = findById(id);
        pessoaEncontrada.setNome(pessoa.getNome());
        pessoaEncontrada.setDataNascimento(pessoa.getDataNascimento());
        return repository.save(pessoaEncontrada);
    }



}
