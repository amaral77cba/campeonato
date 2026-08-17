package com.amarildo.campeonato.dto;

import java.time.LocalDate;

public class RodadaRequestDTO {

    private Long idenFaseCampeonato;
    private Integer ordemRodada;
    private String nomeRodada;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String statusRodada;

    public RodadaRequestDTO() {
    }

    public Long getIdenFaseCampeonato() {
        return idenFaseCampeonato;
    }

    public void setIdenFaseCampeonato(Long idenFaseCampeonato) {
        this.idenFaseCampeonato = idenFaseCampeonato;
    }

    public Integer getOrdemRodada() {
        return ordemRodada;
    }

    public void setOrdemRodada(Integer ordemRodada) {
        this.ordemRodada = ordemRodada;
    }

    public String getNomeRodada() {
        return nomeRodada;
    }

    public void setNomeRodada(String nomeRodada) {
        this.nomeRodada = nomeRodada;
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

    public String getStatusRodada() {
        return statusRodada;
    }

    public void setStatusRodada(String statusRodada) {
        this.statusRodada = statusRodada;
    }
}
