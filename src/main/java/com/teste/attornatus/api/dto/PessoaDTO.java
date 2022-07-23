package com.teste.attornatus.api.dto;

import com.teste.attornatus.api.domain.Endereco;
import com.teste.attornatus.api.domain.Pessoa;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "Representa uma Pessoa no sistema")
public class PessoaDTO {

    @Schema(description = "nome da pessoa")
    @NotNull(message = "{campo.nome.nulo}")
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    @Size(min = 4, max = 60)
    private String nome;

    @Schema(description = "data de nascimento")
    @NotNull(message = "{campo.data_nascimento.nulo}")
    @PastOrPresent(message = "{campo.data.invalida}")
    private LocalDate dataNascimento;

    private List<EnderecoDTO> enderecos;

    public PessoaDTO() {
    }

    public PessoaDTO(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public PessoaDTO(String nome, LocalDate dataNascimento, List<EnderecoDTO> enderecos) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public static Pessoa convertToPessoa(PessoaDTO dto){
        return new Pessoa(dto.getNome(), dto.getDataNascimento());
    }



    public static List<Endereco> convertToList(List<EnderecoDTO> enderecosDtos){
        List<Endereco>lista = new ArrayList<>();
        enderecosDtos.forEach(enderecoDTO -> {
            lista.add(EnderecoDTO.convertTo(enderecoDTO));
;        });
        return lista;
    }


}
