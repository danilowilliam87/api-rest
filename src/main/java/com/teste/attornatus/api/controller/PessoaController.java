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

    @RequestMapping(method = RequestMethod.POST)
    public Pessoa save(@RequestBody @Valid PessoaDTO pessoaDTO){
        return service.save(PessoaDTO.convertToPessoa(pessoaDTO));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Pessoa findById(@PathVariable  Long id){
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Pessoa findByNome(@RequestParam(value = "nome") String nome){
        return service.findByNome(nome);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Pessoa updateFull(@PathVariable Long id, @RequestBody @Valid PessoaDTO pessoaDTO){
        return service.updateFull(id, PessoaDTO.convertToPessoa(pessoaDTO));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public Pessoa updateIncremental(@PathVariable Long id, @RequestBody @Valid Pessoa pessoa){
        return service.updateIncremental(id, pessoa);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Pessoa> findAll(){
       return service.findAll();
    }


    @RequestMapping(value = "/enderecos", method = RequestMethod.POST)
    public Endereco saveEndereco(@RequestBody  EnderecoDTO dto){
        Pessoa pessoaProcurada = service.findById(dto.getPessoaId());
        Endereco endereco = EnderecoDTO.convertTo(dto);
        endereco.setPessoa(pessoaProcurada);
        return enderecoService.save(endereco);
    }

    @RequestMapping(value = "/enderecos", method = RequestMethod.PUT)
    public void updateIncrementalEndereco(@RequestParam(value = "enderecoId") Long idEndereco,
                                          @RequestParam(value = "pessoaId") Long idPessoa){
        enderecoService.updateIncrementalEndereco(idEndereco, idPessoa);
    }

    @RequestMapping(value = "/{id}/enderecos", method = RequestMethod.GET)
    public List<Endereco> findAllEnderecos(@PathVariable(value = "id") Long idPessoa){
        return enderecoService.findAllByPessoa(idPessoa);
    }

}
