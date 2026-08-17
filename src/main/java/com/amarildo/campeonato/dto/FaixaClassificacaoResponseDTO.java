package com.amarildo.campeonato.dto;

public class FaixaClassificacaoResponseDTO {

    private Long idenFaixaClassificacao;
    private Long idenFaseCampeonato;
    private String nomeFaseCampeonato;
    private String nomeFaixa;
    private String codigoCor;

    public FaixaClassificacaoResponseDTO() {
    }

    public Long getIdenFaixaClassificacao() {
        return idenFaixaClassificacao;
    }

    public void setIdenFaixaClassificacao(Long idenFaixaClassificacao) {
        this.idenFaixaClassificacao = idenFaixaClassificacao;
    }

    public Long getIdenFaseCampeonato() {
        return idenFaseCampeonato;
    }

    public void setIdenFaseCampeonato(Long idenFaseCampeonato) {
        this.idenFaseCampeonato = idenFaseCampeonato;
    }

    public String getNomeFaseCampeonato() {
        return nomeFaseCampeonato;
    }

    public void setNomeFaseCampeonato(String nomeFaseCampeonato) {
        this.nomeFaseCampeonato = nomeFaseCampeonato;
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
