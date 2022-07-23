package com.teste.attornatus.api.dto;

import com.teste.attornatus.api.domain.Endereco;
import com.teste.attornatus.api.domain.Pessoa;
import com.teste.attornatus.api.enuns.EnderecoPrincipal;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Representa um endereço no sistema")
public class EnderecoDTO {

    @Schema(description = "logradouro")
    @NotEmpty(message = "{campo.logradouro.vazio}")
    @NotBlank(message = "{campo.logradouro.obrigatorio}")
    @Size(min = 10, max = 100)
    private String logradouro;

    @Schema(description = "cep")
    @NotEmpty(message = "{campo.cep.vazio}")
    @NotBlank(message = "{campo.cep.obrigatorio}")
    @Size(min = 9, max = 9)
    private String cep;

    @Schema(description = "numero")
    @NotNull(message = "{campo.vazio.obrigatorio}")
    private Integer numero;

    @Schema(description = "cidade")
    @NotEmpty(message = "{campo.cidade.vazio}")
    @NotBlank(message = "{campo.cidade.obrigatorio}")
    @Size(min = 4, max = 100)
    private String cidade;


    @Schema(description = "campo que define se o endereço é o principal")
   @NotNull(message = "{campo.principal.obrigatorio}")
   private EnderecoPrincipal principal;

    @Schema(description = "id da pessoa que pertencerá o endereço")
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
