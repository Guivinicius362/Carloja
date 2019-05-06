package com.teste.carloja.log;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogModel {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hora;
    private Double valor;
    private Double valorTotal;
    private String session;

    public LogModel() {
    }

    public LogModel(Long id, String hora, Double valor, String session) {
        this.id = id;
        this.hora = hora;
        this.valor = valor;
        this.valorTotal = valorTotal;
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public void setValor(Double valor) {
        this.valor = valor;
    }


    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "id=" + id +
                ", hora=" + hora +
                ", valor=" + valor +
                ", valorTotal=" + valorTotal +
                ", session='" + session + '\'' +
                '}';
    }
}
