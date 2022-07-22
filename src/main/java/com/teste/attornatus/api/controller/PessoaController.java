package com.teste.attornatus.api.controller;

import com.teste.attornatus.api.domain.Endereco;
import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.dto.EnderecoDTO;
import com.teste.attornatus.api.dto.PessoaDTO;
import com.teste.attornatus.api.service.EnderecoService;
import com.teste.attornatus.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

    @Autowired
    PessoaService service;

    @Autowired
    EnderecoService enderecoService;

    @PostMapping
    public Pessoa save(@RequestBody @Valid PessoaDTO pessoaDTO){
        return service.save(PessoaDTO.convertToPessoa(pessoaDTO));
    }

    @GetMapping(value = "/{id}")
    public Pessoa findById(@PathVariable  Long id){
        return service.findById(id);
    }

    @GetMapping
    public Pessoa findByNome(@RequestParam(value = "nome") String nome){
        return service.findByNome(nome);
    }

    @PutMapping(value = "/{id}")
    public Pessoa updateFull(@PathVariable Long id, @RequestBody @Valid PessoaDTO pessoaDTO){
        return service.updateFull(id, PessoaDTO.convertToPessoa(pessoaDTO));
    }

    @PatchMapping(value = "/{id}")
    public Pessoa updateIncremental(@PathVariable Long id, @RequestBody  PessoaDTO pessoaDTO){
        return service.updateIncremental(id, PessoaDTO.convertToPessoa(pessoaDTO));
    }

    @GetMapping(value = "/all")
    public List<Pessoa> findAll(){
       return service.findAll();
    }


    @PostMapping(value = "/enderecos")
    public Endereco saveEndereco(@RequestBody  @Valid EnderecoDTO dto){
        Pessoa pessoaProcurada = service.findById(dto.getPessoaId());
        Endereco endereco = EnderecoDTO.convertTo(dto);
        endereco.setPessoa(pessoaProcurada);
        return enderecoService.save(endereco);
    }

    @PutMapping(value = "/enderecos")
    public void updateIncrementalEndereco(@RequestParam(value = "enderecoId") Long idEndereco,
                                          @RequestParam(value = "pessoaId") Long idPessoa){
        enderecoService.updateIncrementalEndereco(idEndereco, idPessoa);
    }

    @GetMapping(value = "/{id}/enderecos")
    public List<Endereco> findAllEnderecos(@PathVariable(value = "id") Long idPessoa){
        return enderecoService.findAllByPessoa(idPessoa);
    }

}
