package com.amarildo.campeonato.dto;

public class RegraPontuacaoRequestDTO {

    private Long idenFaseCampeonato;
    private Integer pontosVitoria;
    private Integer pontosEmpate;
    private Integer pontosDerrota;

    public RegraPontuacaoRequestDTO() {
    }

    public Long getIdenFaseCampeonato() {
        return idenFaseCampeonato;
    }

    public void setIdenFaseCampeonato(Long idenFaseCampeonato) {
        this.idenFaseCampeonato = idenFaseCampeonato;
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
