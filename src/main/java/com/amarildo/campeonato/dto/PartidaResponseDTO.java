package com.amarildo.campeonato.dto;

import java.time.LocalDateTime;

public class PartidaResponseDTO {

    private Long idenPartida;
    private Long idenRodada;
    private String nomeRodada;
    private Integer ordemRodada;
    private Long idenCampeonatoEquipeMandante;
    private String nomeEquipeMandante;
    private Long idenCampeonatoEquipeVisitante;
    private String nomeEquipeVisitante;
    private LocalDateTime dataHoraPartida;
    private Long idenEstadio;
    private String nomeEstadio;
    private String statusPartida;

    public PartidaResponseDTO() {
    }

    public Long getIdenPartida() {
        return idenPartida;
    }

    public void setIdenPartida(Long idenPartida) {
        this.idenPartida = idenPartida;
    }

    public Long getIdenRodada() {
        return idenRodada;
    }

    public void setIdenRodada(Long idenRodada) {
        this.idenRodada = idenRodada;
    }

    public String getNomeRodada() {
        return nomeRodada;
    }

    public void setNomeRodada(String nomeRodada) {
        this.nomeRodada = nomeRodada;
    }

    public Integer getOrdemRodada() {
        return ordemRodada;
    }

    public void setOrdemRodada(Integer ordemRodada) {
        this.ordemRodada = ordemRodada;
    }

    public Long getIdenCampeonatoEquipeMandante() {
        return idenCampeonatoEquipeMandante;
    }

    public void setIdenCampeonatoEquipeMandante(Long idenCampeonatoEquipeMandante) {
        this.idenCampeonatoEquipeMandante = idenCampeonatoEquipeMandante;
    }

    public String getNomeEquipeMandante() {
        return nomeEquipeMandante;
    }

    public void setNomeEquipeMandante(String nomeEquipeMandante) {
        this.nomeEquipeMandante = nomeEquipeMandante;
    }

    public Long getIdenCampeonatoEquipeVisitante() {
        return idenCampeonatoEquipeVisitante;
    }

    public void setIdenCampeonatoEquipeVisitante(Long idenCampeonatoEquipeVisitante) {
        this.idenCampeonatoEquipeVisitante = idenCampeonatoEquipeVisitante;
    }

    public String getNomeEquipeVisitante() {
        return nomeEquipeVisitante;
    }

    public void setNomeEquipeVisitante(String nomeEquipeVisitante) {
        this.nomeEquipeVisitante = nomeEquipeVisitante;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public void setDataHoraPartida(LocalDateTime dataHoraPartida) {
        this.dataHoraPartida = dataHoraPartida;
    }

    public Long getIdenEstadio() {
        return idenEstadio;
    }

    public void setIdenEstadio(Long idenEstadio) {
        this.idenEstadio = idenEstadio;
    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }

    public String getStatusPartida() {
        return statusPartida;
    }

    public void setStatusPartida(String statusPartida) {
        this.statusPartida = statusPartida;
    }
}
