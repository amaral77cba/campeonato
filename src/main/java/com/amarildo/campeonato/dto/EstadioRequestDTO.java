package com.amarildo.campeonato.dto;

public class EstadioRequestDTO {

    private String nomeEstadio;

    private String apelidoEstadio;

    private Long idenCidade;

    public EstadioRequestDTO() {
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
}
