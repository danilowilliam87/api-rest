package com.teste.attornatus.api.dto;

import com.teste.attornatus.api.domain.Endereco;
import com.teste.attornatus.api.domain.Pessoa;

public class EnderecoDTO {

    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private boolean principal;
    private Long pessoaId;

    public EnderecoDTO() {
    }

    public EnderecoDTO(String logradouro, String cep, Integer numero,
                       String cidade, boolean principal, Long pessoaId) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.principal = principal;
        this.pessoaId = pessoaId;
    }

    public static Endereco convertTo(EnderecoDTO dto) {
        return new Endereco(dto.getLogradouro(), dto.getCep(), dto.getNumero(), dto.getCidade(),
                new Pessoa(dto.getPessoaId()), dto.isPrincipal());
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

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }



}
