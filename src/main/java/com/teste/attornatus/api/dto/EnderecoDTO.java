package com.teste.attornatus.api.dto;

import com.teste.attornatus.api.domain.Endereco;
import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.enuns.EnderecoPrincipal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EnderecoDTO {

    @NotEmpty(message = "{campo.logradouro.vazio}")
    @NotBlank(message = "{campo.logradouro.obrigatorio}")
    private String logradouro;

    @NotEmpty(message = "{campo.cep.vazio}")
    @NotBlank(message = "{campo.cep.obrigatorio}")
    private String cep;

    @NotNull(message = "{campo.vazio.obrigatorio}")
    private Integer numero;
    @NotEmpty(message = "{campo.cidade.vazio}")
    @NotBlank(message = "{campo.cidade.obrigatorio}")
    private String cidade;


   @NotNull(message = "{campo.principal.obrigatorio}")
    private EnderecoPrincipal principal;

    @NotNull(message = "{campo.pessoaId.obrigatorio}")
    private Long pessoaId;

    public EnderecoDTO() {
    }

    public EnderecoDTO(String logradouro, String cep, Integer numero,
                       String cidade, EnderecoPrincipal principal, Long pessoaId) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.principal = principal;
        this.pessoaId = pessoaId;
    }

    public static Endereco convertTo(EnderecoDTO dto) {
        return new Endereco(dto.getLogradouro(), dto.getCep(), dto.getNumero(), dto.getCidade(),
                new Pessoa(dto.getPessoaId()), String.valueOf(dto.getPrincipal().getStatus()));
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public EnderecoPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(EnderecoPrincipal principal) {
        this.principal = principal;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }



}
