package com.amarildo.campeonato.entity;

import com.amarildo.campeonato.entity.enums.StatusRodada;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rodada",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_rodada_ordem",
                columnNames = {"iden_fasecampeonato", "ordem_rodada"}
        ))
public class Rodada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_rodada")
    private Long idenRodada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_fasecampeonato", nullable = false)
    private FaseCampeonato faseCampeonato;

    @Column(name = "ordem_rodada", nullable = false)
    private Integer ordemRodada;

    @Column(name = "nome_rodada", length = 100)
    private String nomeRodada;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_rodada", nullable = false, length = 20)
    private StatusRodada statusRodada;

    public Rodada() {
    }

    public Long getIdenRodada() {
        return idenRodada;
    }

    public void setIdenRodada(Long idenRodada) {
        this.idenRodada = idenRodada;
    }

    public FaseCampeonato getFaseCampeonato() {
        return faseCampeonato;
    }

    public void setFaseCampeonato(FaseCampeonato faseCampeonato) {
        this.faseCampeonato = faseCampeonato;
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

    public StatusRodada getStatusRodada() {
        return statusRodada;
    }

    public void setStatusRodada(StatusRodada statusRodada) {
        this.statusRodada = statusRodada;
    }
}
