package com.amarildo.campeonato.dto;

public class CidadeResponseDTO {

    private Long idenCidade;
    private String nomeCidade;
    private String uf;

    public CidadeResponseDTO() {
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
