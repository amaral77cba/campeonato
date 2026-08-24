package com.amarildo.campeonato.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resultadopartida",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_resultadopartida_partida",
                columnNames = "iden_partida"
        ))
public class ResultadoPartida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_resultadopartida")
    private Long idenResultadoPartida;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_partida", nullable = false)
    private Partida partida;

    @Column(name = "gols_equipe_mandante", nullable = false)
    private Integer golsEquipeMandante;

    @Column(name = "gols_equipe_visitante", nullable = false)
    private Integer golsEquipeVisitante;

    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;

    public ResultadoPartida() {
    }

    public Long getIdenResultadoPartida() {
        return idenResultadoPartida;
    }

    public void setIdenResultadoPartida(Long idenResultadoPartida) {
        this.idenResultadoPartida = idenResultadoPartida;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
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
