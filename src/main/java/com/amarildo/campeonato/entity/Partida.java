package com.amarildo.campeonato.entity;

import com.amarildo.campeonato.entity.enums.StatusPartida;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "partida",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_partida_rodada_equipes",
                columnNames = {"iden_rodada", "iden_campeonatoequipe_mandante", "iden_campeonatoequipe_visitante"}
        ))
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_partida")
    private Long idenPartida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_rodada", nullable = false)
    private Rodada rodada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_campeonatoequipe_mandante", nullable = false)
    private CampeonatoEquipe campeonatoEquipeMandante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_campeonatoequipe_visitante", nullable = false)
    private CampeonatoEquipe campeonatoEquipeVisitante;

    @Column(name = "data_hora_partida")
    private LocalDateTime dataHoraPartida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_estadio")
    private Estadio estadio;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_partida", nullable = false, length = 20)
    private StatusPartida statusPartida;

    public Partida() {
    }

    public Long getIdenPartida() {
        return idenPartida;
    }

    public void setIdenPartida(Long idenPartida) {
        this.idenPartida = idenPartida;
    }

    public Rodada getRodada() {
        return rodada;
    }

    public void setRodada(Rodada rodada) {
        this.rodada = rodada;
    }

    public CampeonatoEquipe getCampeonatoEquipeMandante() {
        return campeonatoEquipeMandante;
    }

    public void setCampeonatoEquipeMandante(CampeonatoEquipe campeonatoEquipeMandante) {
        this.campeonatoEquipeMandante = campeonatoEquipeMandante;
    }

    public CampeonatoEquipe getCampeonatoEquipeVisitante() {
        return campeonatoEquipeVisitante;
    }

    public void setCampeonatoEquipeVisitante(CampeonatoEquipe campeonatoEquipeVisitante) {
        this.campeonatoEquipeVisitante = campeonatoEquipeVisitante;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public void setDataHoraPartida(LocalDateTime dataHoraPartida) {
        this.dataHoraPartida = dataHoraPartida;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public StatusPartida getStatusPartida() {
        return statusPartida;
    }

    public void setStatusPartida(StatusPartida statusPartida) {
        this.statusPartida = statusPartida;
    }
}
