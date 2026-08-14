package com.amarildo.campeonato.entity;

import com.amarildo.campeonato.entity.enums.StatusParticipacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "campeonatoequipe",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_campeonatoequipe_campeonato_equipe",
                columnNames = {"iden_campeonato", "iden_equipe"}
        ))
public class CampeonatoEquipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_campeonatoequipe")
    private Long idenCampeonatoEquipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_campeonato", nullable = false)
    private Campeonato campeonato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_equipe", nullable = false)
    private Equipe equipe;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_inscricao", nullable = false)
    private LocalDate dataInscricao;

    @Column(name = "cabeca_chave", nullable = false)
    private Boolean cabecaChave;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_participacao", nullable = false, length = 20)
    private StatusParticipacao statusParticipacao;

    public CampeonatoEquipe() {
    }

    public Long getIdenCampeonatoEquipe() {
        return idenCampeonatoEquipe;
    }

    public void setIdenCampeonatoEquipe(Long idenCampeonatoEquipe) {
        this.idenCampeonatoEquipe = idenCampeonatoEquipe;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
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

    public StatusParticipacao getStatusParticipacao() {
        return statusParticipacao;
    }

    public void setStatusParticipacao(StatusParticipacao statusParticipacao) {
        this.statusParticipacao = statusParticipacao;
    }
}
