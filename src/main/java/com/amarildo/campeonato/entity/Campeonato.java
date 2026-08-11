package com.amarildo.campeonato.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "campeonato",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_campeonato_nome_ano",
                columnNames = {"nome_campeonato", "ano"}
        ))
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_campeonato")
    private Long idenCampeonato;

    @Column(name = "nome_campeonato", nullable = false, length = 200)
    private String nomeCampeonato;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_fim")
    private LocalDate dataFim;

    public Campeonato() {
    }

    public Long getIdenCampeonato() {
        return idenCampeonato;
    }

    public void setIdenCampeonato(Long idenCampeonato) {
        this.idenCampeonato = idenCampeonato;
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
