package com.amarildo.campeonato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "equipe")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_equipe")
    private Long idenEquipe;

    @Column(name = "nome_equipe", nullable = false, length = 200)
    private String nomeEquipe;

    @Column(name = "sigla_equipe", length = 10)
    private String siglaEquipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_cidade", nullable = false)
    private Cidade cidade;

    @Column(name = "caminho_escudo", length = 500)
    private String caminhoEscudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_estadio_padrao")
    private Estadio estadioPadrao;

    public Equipe() {
    }

    public Long getIdenEquipe() {
        return idenEquipe;
    }

    public void setIdenEquipe(Long idenEquipe) {
        this.idenEquipe = idenEquipe;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public String getSiglaEquipe() {
        return siglaEquipe;
    }

    public void setSiglaEquipe(String siglaEquipe) {
        this.siglaEquipe = siglaEquipe;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getCaminhoEscudo() {
        return caminhoEscudo;
    }

    public void setCaminhoEscudo(String caminhoEscudo) {
        this.caminhoEscudo = caminhoEscudo;
    }

    public Estadio getEstadioPadrao() {
        return estadioPadrao;
    }

    public void setEstadioPadrao(Estadio estadioPadrao) {
        this.estadioPadrao = estadioPadrao;
    }
}
