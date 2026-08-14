package com.amarildo.campeonato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fasecampeonato",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_fasecampeonato_ordem",
                columnNames = {"iden_campeonato", "ordem"}
        ))
public class FaseCampeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_fasecampeonato")
    private Long idenFaseCampeonato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_campeonato", nullable = false)
    private Campeonato campeonato;

    @Column(name = "nome_fasecampeonato", nullable = false, length = 200)
    private String nomeFaseCampeonato;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;

    @Column(name = "qtdclassificam")
    private Integer qtdClassificam;

    @Column(name = "possuiidavolta", nullable = false)
    private Boolean possuiIdaVolta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_tipodisputa", nullable = false)
    private TipoDisputa tipoDisputa;

    public FaseCampeonato() {
    }

    public Long getIdenFaseCampeonato() {
        return idenFaseCampeonato;
    }

    public void setIdenFaseCampeonato(Long idenFaseCampeonato) {
        this.idenFaseCampeonato = idenFaseCampeonato;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public String getNomeFaseCampeonato() {
        return nomeFaseCampeonato;
    }

    public void setNomeFaseCampeonato(String nomeFaseCampeonato) {
        this.nomeFaseCampeonato = nomeFaseCampeonato;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Integer getQtdClassificam() {
        return qtdClassificam;
    }

    public void setQtdClassificam(Integer qtdClassificam) {
        this.qtdClassificam = qtdClassificam;
    }

    public Boolean getPossuiIdaVolta() {
        return possuiIdaVolta;
    }

    public void setPossuiIdaVolta(Boolean possuiIdaVolta) {
        this.possuiIdaVolta = possuiIdaVolta;
    }

    public TipoDisputa getTipoDisputa() {
        return tipoDisputa;
    }

    public void setTipoDisputa(TipoDisputa tipoDisputa) {
        this.tipoDisputa = tipoDisputa;
    }
}
