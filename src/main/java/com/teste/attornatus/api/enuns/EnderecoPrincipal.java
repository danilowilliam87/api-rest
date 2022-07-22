package com.teste.attornatus.api.enuns;

public enum EnderecoPrincipal {



     SIM("SIM"),
     NAO("NAO");
    private String status;

    EnderecoPrincipal(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
