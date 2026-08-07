package com.amarildo.campeonato.dto;

public class CidadeRequestDTO {

    private String nomeCidade;
    private String uf;

    public CidadeRequestDTO() {
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
