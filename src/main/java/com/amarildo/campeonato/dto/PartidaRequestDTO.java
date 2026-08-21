package com.amarildo.campeonato.dto;

import java.time.LocalDateTime;

public class PartidaRequestDTO {

    private Long idenRodada;
    private Long idenCampeonatoEquipeMandante;
    private Long idenCampeonatoEquipeVisitante;
    private LocalDateTime dataHoraPartida;
    private Long idenEstadio;
    private String statusPartida;

    public PartidaRequestDTO() {
    }

    public Long getIdenRodada() {
        return idenRodada;
    }

    public void setIdenRodada(Long idenRodada) {
        this.idenRodada = idenRodada;
    }

    public Long getIdenCampeonatoEquipeMandante() {
        return idenCampeonatoEquipeMandante;
    }

    public void setIdenCampeonatoEquipeMandante(Long idenCampeonatoEquipeMandante) {
        this.idenCampeonatoEquipeMandante = idenCampeonatoEquipeMandante;
    }

    public Long getIdenCampeonatoEquipeVisitante() {
        return idenCampeonatoEquipeVisitante;
    }

    public void setIdenCampeonatoEquipeVisitante(Long idenCampeonatoEquipeVisitante) {
        this.idenCampeonatoEquipeVisitante = idenCampeonatoEquipeVisitante;
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

    public String getStatusPartida() {
        return statusPartida;
    }

    public void setStatusPartida(String statusPartida) {
        this.statusPartida = statusPartida;
    }
}
