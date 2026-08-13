package com.amarildo.campeonato.dto;

public class EquipeRequestDTO {

    private String nomeEquipe;

    private String siglaEquipe;

    private Long idenCidade;

    private String caminhoEscudo;

    private Long idenEstadioPadrao;

    public EquipeRequestDTO() {
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

    public Long getIdenCidade() {
        return idenCidade;
    }

    public void setIdenCidade(Long idenCidade) {
        this.idenCidade = idenCidade;
    }

    public String getCaminhoEscudo() {
        return caminhoEscudo;
    }

    public void setCaminhoEscudo(String caminhoEscudo) {
        this.caminhoEscudo = caminhoEscudo;
    }

    public Long getIdenEstadioPadrao() {
        return idenEstadioPadrao;
    }

    public void setIdenEstadioPadrao(Long idenEstadioPadrao) {
        this.idenEstadioPadrao = idenEstadioPadrao;
    }
}
