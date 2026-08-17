package com.amarildo.campeonato.dto;

public class FaixaClassificacaoRequestDTO {

    private Long idenFaseCampeonato;
    private String nomeFaixa;
    private String codigoCor;

    public FaixaClassificacaoRequestDTO() {
    }

    public Long getIdenFaseCampeonato() {
        return idenFaseCampeonato;
    }

    public void setIdenFaseCampeonato(Long idenFaseCampeonato) {
        this.idenFaseCampeonato = idenFaseCampeonato;
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
