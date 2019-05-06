package com.teste.carloja.error;

public class ApiException extends Exception {

    private String mensagem;
    private Integer statusCode;

    public ApiException(String mensagem, Integer statusCode) {
        this.mensagem = mensagem;
        this.statusCode = statusCode;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
