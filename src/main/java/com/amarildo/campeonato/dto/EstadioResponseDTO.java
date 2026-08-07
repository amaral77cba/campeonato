package com.amarildo.campeonato.dto;

public class EstadioResponseDTO {

    private Long idenEstadio;
    private String nomeEstadio;
    private String apelidoEstadio;
    private Long idenCidade;
    private String nomeCidade;


    public Long getIdenEstadio() {
        return idenEstadio;
    }

    public void setIdenEstadio(Long idenEstadio) {
        this.idenEstadio = idenEstadio;
    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }

    public String getApelidoEstadio() {
        return apelidoEstadio;
    }

    public void setApelidoEstadio(String apelidoEstadio) {
        this.apelidoEstadio = apelidoEstadio;
    }

    public Long getIdenCidade() {
        return idenCidade;
    }

    public void setIdenCidade(Long idenCidade) {
        this.idenCidade = idenCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
}