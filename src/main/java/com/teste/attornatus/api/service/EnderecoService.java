package com.teste.attornatus.api.service;

import com.teste.attornatus.api.domain.Endereco;
import com.teste.attornatus.api.exception.ResourceNotFoundException;
import com.teste.attornatus.api.exception.ServiceException;
import com.teste.attornatus.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    String sim = "SIM";
    String nao = "NAO";


    public Endereco findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("resource with id " + id + "not found"));
    }
    public List<Endereco> findAllByPessoa(Long idPessoa){
        return repository.findAllByIdPessoa(idPessoa);

    }

    public Endereco save(Endereco endereco){
        validateObject(endereco);
        String sim = "SIM";
        String nao = "NAO";
        List<Endereco> lista = repository.findAllByIdPessoa(endereco.getPessoa().getId());
        if(lista.isEmpty() && endereco.getPrincipal().equals("NAO")){
            endereco.setPrincipal(sim);
        }
        if(!lista.isEmpty() && endereco.getPrincipal().equals("SIM")){
            lista.forEach(l ->{
                l.setPrincipal(nao);
                repository.save(l);
            });
        }
        return repository.save(endereco);
    }

    public void updateIncrementalEndereco(Long enderecoId, Long pessoaId){

        if(findById(enderecoId) != null) {

            List<Endereco> lista = repository.findAllByIdPessoa(pessoaId);
            if (!lista.isEmpty()) {
                lista.forEach(endereco -> {
                    if (enderecoId.equals(endereco.getId())) {
                        endereco.setPrincipal(sim);
                    } else {
                        endereco.setPrincipal(nao);
                    }
                    repository.save(endereco);
                });
            }
        }
    }

    private void validateObject(Endereco endereco){
        if(endereco == null || endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty() || endereco.getCep() == null || endereco.getCep().isEmpty()
           || endereco.getPessoa() == null || endereco.getNumero() == null
          || endereco.getCidade() == null ||  endereco.getCidade().isEmpty()){
            throw new ServiceException("object and yours field cannot be null");
        }
    }



}
