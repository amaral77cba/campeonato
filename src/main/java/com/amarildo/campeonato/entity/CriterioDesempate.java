package com.amarildo.campeonato.entity;

import com.amarildo.campeonato.entity.enums.SentidoOrdenacao;
import jakarta.persistence.*;

@Entity
@Table(name = "criteriodesempate",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_criteriodesempate_nome",
                        columnNames = "nome_criteriodesempate"
                ),
                @UniqueConstraint(
                        name = "uk_criteriodesempate_codigo",
                        columnNames = "codigo_criteriodesempate"
                )
        })
public class CriterioDesempate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_criteriodesempate")
    private Long idenCriterioDesempate;

    @Column(name = "nome_criteriodesempate", nullable = false, length = 100)
    private String nomeCriterioDesempate;

    @Column(name = "codigo_criteriodesempate", nullable = false, length = 50)
    private String codigoCriterioDesempate;

    @Enumerated(EnumType.STRING)
    @Column(name = "sentido_ordenacao", nullable = false, length = 4)
    private SentidoOrdenacao sentidoOrdenacao;

    @Column(name = "calculo_automatico", nullable = false)
    private Boolean calculoAutomatico;

    public CriterioDesempate() {
    }

    public Long getIdenCriterioDesempate() {
        return idenCriterioDesempate;
    }

    public void setIdenCriterioDesempate(Long idenCriterioDesempate) {
        this.idenCriterioDesempate = idenCriterioDesempate;
    }

    public String getNomeCriterioDesempate() {
        return nomeCriterioDesempate;
    }

    public void setNomeCriterioDesempate(String nomeCriterioDesempate) {
        this.nomeCriterioDesempate = nomeCriterioDesempate;
    }

    public String getCodigoCriterioDesempate() {
        return codigoCriterioDesempate;
    }

    public void setCodigoCriterioDesempate(String codigoCriterioDesempate) {
        this.codigoCriterioDesempate = codigoCriterioDesempate;
    }

    public SentidoOrdenacao getSentidoOrdenacao() {
        return sentidoOrdenacao;
    }

    public void setSentidoOrdenacao(SentidoOrdenacao sentidoOrdenacao) {
        this.sentidoOrdenacao = sentidoOrdenacao;
    }

    public Boolean getCalculoAutomatico() {
        return calculoAutomatico;
    }

    public void setCalculoAutomatico(Boolean calculoAutomatico) {
        this.calculoAutomatico = calculoAutomatico;
    }
}
