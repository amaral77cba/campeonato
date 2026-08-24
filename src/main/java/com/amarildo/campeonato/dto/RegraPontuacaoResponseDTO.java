package com.amarildo.campeonato.dto;

public class RegraPontuacaoResponseDTO {

    private Long idenRegraPontuacao;
    private Long idenFaseCampeonato;
    private String nomeFaseCampeonato;
    private Integer pontosVitoria;
    private Integer pontosEmpate;
    private Integer pontosDerrota;

    public RegraPontuacaoResponseDTO() {
    }

    public Long getIdenRegraPontuacao() {
        return idenRegraPontuacao;
    }

    public void setIdenRegraPontuacao(Long idenRegraPontuacao) {
        this.idenRegraPontuacao = idenRegraPontuacao;
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

    public Integer getPontosVitoria() {
        return pontosVitoria;
    }

    public void setPontosVitoria(Integer pontosVitoria) {
        this.pontosVitoria = pontosVitoria;
    }

    public Integer getPontosEmpate() {
        return pontosEmpate;
    }

    public void setPontosEmpate(Integer pontosEmpate) {
        this.pontosEmpate = pontosEmpate;
    }

    public Integer getPontosDerrota() {
        return pontosDerrota;
    }

    public void setPontosDerrota(Integer pontosDerrota) {
        this.pontosDerrota = pontosDerrota;
    }
}
