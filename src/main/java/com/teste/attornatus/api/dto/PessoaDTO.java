package com.teste.attornatus.api.dto;

import com.teste.attornatus.api.domain.Pessoa;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class PessoaDTO {

    @NotNull(message = "{campo.nome.nulo}")
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotNull(message = "{campo.data_nascimento.nulo}")
    @PastOrPresent(message = "{campo.data.invalida}")
    private LocalDate dataNascimento;

    public PessoaDTO() {
    }

    public PessoaDTO(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
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

    public static Pessoa convertToPessoa(PessoaDTO dto){
        return new Pessoa(dto.getNome(), dto.getDataNascimento());
    }
}
