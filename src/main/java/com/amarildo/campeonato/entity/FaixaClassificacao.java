package com.amarildo.campeonato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "faixaclassificacao",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_faixaclassificacao_fase_nome",
                columnNames = {"iden_fasecampeonato", "nome_faixa"}
        ))
public class FaixaClassificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_faixaclassificacao")
    private Long idenFaixaClassificacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_fasecampeonato", nullable = false)
    private FaseCampeonato faseCampeonato;

    @Column(name = "nome_faixa", nullable = false, length = 100)
    private String nomeFaixa;

    @Column(name = "codigo_cor", length = 20)
    private String codigoCor;

    public FaixaClassificacao() {
    }

    public Long getIdenFaixaClassificacao() {
        return idenFaixaClassificacao;
    }

    public void setIdenFaixaClassificacao(Long idenFaixaClassificacao) {
        this.idenFaixaClassificacao = idenFaixaClassificacao;
    }

    public FaseCampeonato getFaseCampeonato() {
        return faseCampeonato;
    }

    public void setFaseCampeonato(FaseCampeonato faseCampeonato) {
        this.faseCampeonato = faseCampeonato;
    }

    public String getNomeFaixa() {
        return nomeFaixa;
    }

    public void setNomeFaixa(String nomeFaixa) {
        this.nomeFaixa = nomeFaixa;
    }

    public String getCodigoCor() {
        return codigoCor;
    }

    public void setCodigoCor(String codigoCor) {
        this.codigoCor = codigoCor;
    }
}
