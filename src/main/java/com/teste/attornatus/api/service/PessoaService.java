package com.teste.attornatus.api.service;

import com.teste.attornatus.api.domain.Endereco;
import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.dto.EnderecoDTO;
import com.teste.attornatus.api.dto.PessoaDTO;
import com.teste.attornatus.api.error.ResourceNotFoundException;
import com.teste.attornatus.api.error.ServiceException;
import com.teste.attornatus.api.repository.EnderecoRepository;
import com.teste.attornatus.api.repository.PessoaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Pessoa save(Pessoa pessoa){

         List<Endereco> lista = new ArrayList<>();
         //verificar se mais de um endereço foi escolhido como principal
         if(pessoa.getEnderecos().size() > 1){
         pessoa.getEnderecos().forEach(endereco -> {
            if(endereco.isPrincipal()){
                lista.add(endereco);
                if(lista.size() > 1){
                    throw new ServiceException("escolha apenas um endereco como principal");
                }
            }
         });
        }

        //definir endereco cadastrado como principal
        if (lista.size() == 1){
            pessoa.getEnderecos().get(0).setPrincipal(true);
        }

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
