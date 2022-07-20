package com.teste.attornatus.api.service;

import com.teste.attornatus.api.domain.Endereco;
import com.teste.attornatus.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public List<Endereco> findAllByPessoa(Long idPessoa){
        return repository.findAllByIdPessoa(idPessoa);

    }

    public Endereco save(Endereco endereco){
       if(endereco.isPrincipal()){
           List<Endereco> lista = repository.findAllByIdPessoa(endereco.getPessoa().getId());
           if(!lista.isEmpty()){
               lista.forEach(endereco1 -> {
                  endereco1.setPrincipal(false);
                  repository.save(endereco1);
               });
           }else {
               endereco.setPrincipal(true);
           }
       }
        return repository.save(endereco);
    }

    public void updateIncrementalEndereco(Long enderecoId, Long pessoaId){
        List<Endereco> lista = repository.findAllByIdPessoa(pessoaId);
        if(!lista.isEmpty()){
            lista.forEach(endereco -> {
                endereco.setPrincipal(endereco.getId().equals(enderecoId));
                repository.save(endereco);
            });
        }

    }
}
