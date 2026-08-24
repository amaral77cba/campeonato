package com.amarildo.campeonato.dto;

import java.time.LocalDateTime;

public class ResultadoPartidaRequestDTO {

    private Long idenPartida;
    private Integer golsEquipeMandante;
    private Integer golsEquipeVisitante;
    private LocalDateTime dataRegistro;

    public ResultadoPartidaRequestDTO() {
    }

    public Long getIdenPartida() {
        return idenPartida;
    }

    public void setIdenPartida(Long idenPartida) {
        this.idenPartida = idenPartida;
    }

    public Integer getGolsEquipeMandante() {
        return golsEquipeMandante;
    }

    public void setGolsEquipeMandante(Integer golsEquipeMandante) {
        this.golsEquipeMandante = golsEquipeMandante;
    }

    public Integer getGolsEquipeVisitante() {
        return golsEquipeVisitante;
    }

    public void setGolsEquipeVisitante(Integer golsEquipeVisitante) {
        this.golsEquipeVisitante = golsEquipeVisitante;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
