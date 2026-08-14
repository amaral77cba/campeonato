package com.amarildo.campeonato.dto;

import java.time.LocalDate;

public class CampeonatoEquipeRequestDTO {

    private Long idenCampeonato;
    private Long idenEquipe;
    private LocalDate dataInscricao;
    private Boolean cabecaChave;
    private String statusParticipacao;

    public CampeonatoEquipeRequestDTO() {
    }

    public Long getIdenCampeonato() {
        return idenCampeonato;
    }

    public void setIdenCampeonato(Long idenCampeonato) {
        this.idenCampeonato = idenCampeonato;
    }

    public Long getIdenEquipe() {
        return idenEquipe;
    }

    public void setIdenEquipe(Long idenEquipe) {
        this.idenEquipe = idenEquipe;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public Boolean getCabecaChave() {
        return cabecaChave;
    }

    public void setCabecaChave(Boolean cabecaChave) {
        this.cabecaChave = cabecaChave;
    }

    public String getStatusParticipacao() {
        return statusParticipacao;
    }

    public void setStatusParticipacao(String statusParticipacao) {
        this.statusParticipacao = statusParticipacao;
    }
}
