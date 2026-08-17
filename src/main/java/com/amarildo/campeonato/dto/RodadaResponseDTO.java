package com.amarildo.campeonato.dto;

import java.time.LocalDate;

public class RodadaResponseDTO {

    private Long idenRodada;
    private Long idenFaseCampeonato;
    private String nomeFaseCampeonato;
    private Integer ordemRodada;
    private String nomeRodada;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String statusRodada;

    public RodadaResponseDTO() {
    }

    public Long getIdenRodada() {
        return idenRodada;
    }

    public void setIdenRodada(Long idenRodada) {
        this.idenRodada = idenRodada;
    }

    public Long getIdenFaseCampeonato() {
        return idenFaseCampeonato;
    }

    public void setIdenFaseCampeonato(Long idenFaseCampeonato) {
        this.idenFaseCampeonato = idenFaseCampeonato;
    }

    public String getNomeFaseCampeonato() {
        return nomeFaseCampeonato;
    }

    public void setNomeFaseCampeonato(String nomeFaseCampeonato) {
        this.nomeFaseCampeonato = nomeFaseCampeonato;
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
