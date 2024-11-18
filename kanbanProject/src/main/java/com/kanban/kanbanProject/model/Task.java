package com.kanban.kanbanProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    Instant dataAgora = Instant.now();

    private String titulo;
    private String descricao;
    private String status = "A fazer";
    private String prioridade;
    private String datalimite;

    public long getId() {
        return id;
    }

    public Instant getDataAgora() {
        return dataAgora;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public String getDatalimite() {
        return datalimite;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataAgora(Instant dataAgora) {
        this.dataAgora = dataAgora;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public void setDatalimite(String datalimite) {
        this.datalimite = datalimite;
    }
}
