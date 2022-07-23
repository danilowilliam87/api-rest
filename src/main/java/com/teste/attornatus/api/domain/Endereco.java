package com.teste.attornatus.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    @Schema(description = "logradouro")
    private String logradouro;
    @Column(nullable = false, length = 9)
    @Schema(description = "cep")
    private String cep;
    @Column(nullable = false)
    private Integer numero;
    @Column(nullable = false, length = 100)
    @Schema(description = "cidade")
    private String cidade;

    @JsonIgnore
    @ManyToOne
    private Pessoa pessoa;

    @Column(name = "endereco_principal", nullable = false, length = 3)
    @Schema(description = "campo que define se o endereço é o principal ou não")
    private String principal;

    public Endereco() {
    }

    public Endereco(Long id, String logradouro, String cep, Integer numero,
                    String cidade, Pessoa pessoa, String principal) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.pessoa = pessoa;
        this.principal = principal;
    }

    public Endereco(String logradouro, String cep, Integer numero,
                    String cidade, Pessoa pessoa, String principal) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.pessoa = pessoa;
        this.principal = principal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return id.equals(endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
