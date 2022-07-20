package com.teste.attornatus.api.error;

public class ServiceException extends RuntimeException{

    private String mensagem;

    public ServiceException() {
    }

    public ServiceException(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
