package com.teste.attornatus.api.controller;

import com.teste.attornatus.api.domain.Endereco;
import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.dto.EnderecoDTO;
import com.teste.attornatus.api.dto.PessoaDTO;
import com.teste.attornatus.api.error.ApiError;
import com.teste.attornatus.api.service.EnderecoService;
import com.teste.attornatus.api.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Pessoas API", description = "Gerenciamento de Pessoas e seus respectivos endereços")

@RestController
@RequestMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

    @Autowired
    PessoaService service;

    @Autowired
    EnderecoService enderecoService;

    @PostMapping
    @Operation(description = "Criar uma Pesssoa",
     responses = {
             @ApiResponse(responseCode = "200", description = "Caso a operação seja realizado com sucesso"),
             @ApiResponse(responseCode = "400", description = "Caso já exista um cadastro de pessoa com o nome informado",
                     content = @Content(schema = @Schema(implementation = ApiError.class)))
     })
    public Pessoa save(@RequestBody @Valid PessoaDTO pessoaDTO){
        return service.save(PessoaDTO.convertToPessoa(pessoaDTO));
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "Localiza uma Pessoa usando o id como parâmetro",
    responses = {
            @ApiResponse(responseCode = "200", description = "Caso exista uma pessoa com o id informado"),
            @ApiResponse(responseCode = "404", description = "Caso não exista uma pessoa com o id informado",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public Pessoa findById(@Parameter(description = "ID da pessoa a ser localizado") @PathVariable  Long id){
        return service.findById(id);
    }

    @GetMapping
    @Operation(description = "Localiza uma Pessoa usando o nome como parâmetro",
    responses = {
            @ApiResponse(responseCode = "200", description = "Caso exista uma pessoa com o nome informado"),
            @ApiResponse(responseCode = "404", description = "Caso não exista uma pessoa com o nome informado",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public Pessoa findByNome(@Parameter(description = "Nome da pessoa a ser localizado")@RequestParam(value = "nome") String nome){
        return service.findByNome(nome);
    }

    @PutMapping(value = "/{id}")
    @Operation(description = "Atualiza todos os dados de uma pessoa ",
    responses = {
            @ApiResponse(responseCode = "200", description = "Caso a operação seja realizada co sucesso"),
            @ApiResponse(responseCode = "404", description = "Caso não exista o recurso buscado para atualizar ou haja erro na validação",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public Pessoa updateFull(@PathVariable Long id, @RequestBody @Valid PessoaDTO pessoaDTO){
        return service.updateFull(id, PessoaDTO.convertToPessoa(pessoaDTO));
    }

    @PatchMapping(value = "/{id}")
    @Operation(description = "Atualiza apenas dos dados da pessoa que são informados.",
    responses = {
            @ApiResponse(responseCode = "200", description = "Caso a operação seja realizada co sucesso"),
            @ApiResponse(responseCode = "404", description = "Caso não exista o recurso buscado para atualizar",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public Pessoa updateIncremental(@PathVariable Long id, @RequestBody  PessoaDTO pessoaDTO){
        return service.updateIncremental(id, PessoaDTO.convertToPessoa(pessoaDTO));
    }

    @GetMapping(value = "/all")
    @Operation(description = "Localiza todas as pessoas cadastradas")
    public List<Pessoa> findAll(){
       return service.findAll();
    }


    @PostMapping(value = "/enderecos")
    @Operation(description = "Cria um endereço")
    public Endereco saveEndereco(@RequestBody  @Valid EnderecoDTO dto){
        Pessoa pessoaProcurada = service.findById(dto.getPessoaId());
        Endereco endereco = EnderecoDTO.convertTo(dto);
        endereco.setPessoa(pessoaProcurada);
        return enderecoService.save(endereco);
    }

    @PutMapping(value = "/enderecos")
    @Operation(description = "Defini um endereço como principal usando o id do endereço e id da pessoa como parâmetro",
    responses = {
            @ApiResponse(responseCode = "200", description = "Caso a operação seja realizada co sucesso"),
            @ApiResponse(responseCode = "404", description = "Caso não exista o recurso buscado para atualizar",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public void updateIncrementalEndereco(@RequestParam(value = "enderecoId") Long idEndereco,
                                          @RequestParam(value = "pessoaId") Long idPessoa){
        enderecoService.updateIncrementalEndereco(idEndereco, idPessoa);
    }

    @GetMapping(value = "/{id}/enderecos")
    @Operation(description = "Retorna todos os endereços de uma pessoa",
    responses = {
            @ApiResponse(responseCode = "200", description = "Caso a operação seja realizada co sucesso"),
            @ApiResponse(responseCode = "404", description = "Caso não exista o recurso associado a pessoa",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public List<Endereco> findAllEnderecos(@PathVariable(value = "id") Long idPessoa){
        return enderecoService.findAllByPessoa(idPessoa);
    }

}
