package com.amarildo.campeonato.dto;

import java.time.LocalDateTime;

public class ResultadoPartidaResponseDTO {

    private Long idenResultadoPartida;
    private Long idenPartida;
    private Long idenCampeonatoEquipeMandante;
    private String nomeEquipeMandante;
    private Long idenCampeonatoEquipeVisitante;
    private String nomeEquipeVisitante;
    private Integer golsEquipeMandante;
    private Integer golsEquipeVisitante;
    private LocalDateTime dataRegistro;

    public ResultadoPartidaResponseDTO() {
    }

    public Long getIdenResultadoPartida() {
        return idenResultadoPartida;
    }

    public void setIdenResultadoPartida(Long idenResultadoPartida) {
        this.idenResultadoPartida = idenResultadoPartida;
    }

    public Long getIdenPartida() {
        return idenPartida;
    }

    public void setIdenPartida(Long idenPartida) {
        this.idenPartida = idenPartida;
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
