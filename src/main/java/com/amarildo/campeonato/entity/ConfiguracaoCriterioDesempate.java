package com.amarildo.campeonato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "configuracaocriteriodesempate",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_configcriterio_fase_criterio",
                        columnNames = {"iden_fasecampeonato", "iden_criteriodesempate"}
                ),
                @UniqueConstraint(
                        name = "uk_configcriterio_fase_ordem",
                        columnNames = {"iden_fasecampeonato", "ordem"}
                )
        })
public class ConfiguracaoCriterioDesempate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_configcriteriodesempate")
    private Long idenConfigCriterioDesempate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_fasecampeonato", nullable = false)
    private FaseCampeonato faseCampeonato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_criteriodesempate", nullable = false)
    private CriterioDesempate criterioDesempate;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;

    public ConfiguracaoCriterioDesempate() {
    }

    public Long getIdenConfigCriterioDesempate() {
        return idenConfigCriterioDesempate;
    }

    public void setIdenConfigCriterioDesempate(Long idenConfigCriterioDesempate) {
        this.idenConfigCriterioDesempate = idenConfigCriterioDesempate;
    }

    public FaseCampeonato getFaseCampeonato() {
        return faseCampeonato;
    }

    public void setFaseCampeonato(FaseCampeonato faseCampeonato) {
        this.faseCampeonato = faseCampeonato;
    }

    public CriterioDesempate getCriterioDesempate() {
        return criterioDesempate;
    }

    public void setCriterioDesempate(CriterioDesempate criterioDesempate) {
        this.criterioDesempate = criterioDesempate;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
