package com.amarildo.campeonato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "regrapontuacao",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_regrapontuacao_fasecampeonato",
                columnNames = "iden_fasecampeonato"
        ))
public class RegraPontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_regrapontuacao")
    private Long idenRegraPontuacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_fasecampeonato", nullable = false)
    private FaseCampeonato faseCampeonato;

    @Column(name = "pontos_vitoria", nullable = false)
    private Integer pontosVitoria;

    @Column(name = "pontos_empate", nullable = false)
    private Integer pontosEmpate;

    @Column(name = "pontos_derrota", nullable = false)
    private Integer pontosDerrota;

    public RegraPontuacao() {
    }

    public Long getIdenRegraPontuacao() {
        return idenRegraPontuacao;
    }

    public void setIdenRegraPontuacao(Long idenRegraPontuacao) {
        this.idenRegraPontuacao = idenRegraPontuacao;
    }

    public FaseCampeonato getFaseCampeonato() {
        return faseCampeonato;
    }

    public void setFaseCampeonato(FaseCampeonato faseCampeonato) {
        this.faseCampeonato = faseCampeonato;
    }

    public Integer getPontosVitoria() {
        return pontosVitoria;
    }

    public void setPontosVitoria(Integer pontosVitoria) {
        this.pontosVitoria = pontosVitoria;
    }

    public Integer getPontosEmpate() {
        return pontosEmpate;
    }

    public void setPontosEmpate(Integer pontosEmpate) {
        this.pontosEmpate = pontosEmpate;
    }

    public Integer getPontosDerrota() {
        return pontosDerrota;
    }

    public void setPontosDerrota(Integer pontosDerrota) {
        this.pontosDerrota = pontosDerrota;
    }
}
