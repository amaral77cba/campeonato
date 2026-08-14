package com.amarildo.campeonato.dto;

import java.time.LocalDate;

public class CampeonatoEquipeResponseDTO {

    private Long idenCampeonatoEquipe;
    private Long idenCampeonato;
    private String nomeCampeonato;
    private Long idenEquipe;
    private String nomeEquipe;
    private LocalDate dataInscricao;
    private Boolean cabecaChave;
    private String statusParticipacao;

    public CampeonatoEquipeResponseDTO() {
    }

    public Long getIdenCampeonatoEquipe() {
        return idenCampeonatoEquipe;
    }

    public void setIdenCampeonatoEquipe(Long idenCampeonatoEquipe) {
        this.idenCampeonatoEquipe = idenCampeonatoEquipe;
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

    public Long getIdenEquipe() {
        return idenEquipe;
    }

    public void setIdenEquipe(Long idenEquipe) {
        this.idenEquipe = idenEquipe;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
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
