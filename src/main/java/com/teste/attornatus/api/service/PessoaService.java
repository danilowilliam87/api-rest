package com.teste.attornatus.api.service;

import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.exception.ResourceNotFoundException;
import com.teste.attornatus.api.exception.ResultUniqueException;
import com.teste.attornatus.api.exception.ServiceException;
import com.teste.attornatus.api.repository.EnderecoRepository;
import com.teste.attornatus.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Pessoa save(Pessoa pessoa){
        validateObject(pessoa);
        Optional<Pessoa> busca = repository.findByNome(pessoa.getNome());
        if(busca.isPresent()){
            throw new ResultUniqueException("1 resource found with this name : " + pessoa.getNome());
        }
        return repository.save(pessoa);
    }

    public Pessoa findById(Long id) {
      return repository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("resource with id : " + id + " not found"));
    }

    public Pessoa findByNome(String nome)  {
        return repository.findByNome(nome)
                .orElseThrow(()-> new ResourceNotFoundException("resource with name : " + nome + " not found"));
    }

    public List<Pessoa> findAll(){
        return repository.findAll();
    }


    public Pessoa updateIncremental(Long id, Pessoa pessoa){
        Pessoa pessoaEncontrada = findById(id);
        pessoaEncontrada.setNome(pessoa.getNome() == null || pessoa.getNome().trim().length() == 0   ? pessoaEncontrada.getNome() : pessoa.getNome());
        pessoaEncontrada.setDataNascimento(Optional.ofNullable(pessoa.getDataNascimento()).orElse(pessoaEncontrada.getDataNascimento()));
        return repository.save(pessoaEncontrada);
    }

    public Pessoa updateFull(Long id, Pessoa pessoa){
        validateObject(pessoa);
        Pessoa pessoaEncontrada = findById(id);
        pessoaEncontrada.setNome(pessoa.getNome());
        pessoaEncontrada.setDataNascimento(pessoa.getDataNascimento());
        return repository.save(pessoaEncontrada);
    }

    public void validateObject(Pessoa pessoa){
        if(pessoa == null || pessoa.getNome() == null || pessoa.getDataNascimento() == null
           || pessoa.getNome().isEmpty()){
            throw new ServiceException("object and yours filed cannot be null");
        }
    }


}
