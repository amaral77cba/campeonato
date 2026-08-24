package com.amarildo.campeonato.dto;

public class ConfiguracaoCriterioDesempateRequestDTO {

    private Long idenFaseCampeonato;
    private Long idenCriterioDesempate;
    private Integer ordem;

    public ConfiguracaoCriterioDesempateRequestDTO() {
    }

    public Long getIdenFaseCampeonato() {
        return idenFaseCampeonato;
    }

    public void setIdenFaseCampeonato(Long idenFaseCampeonato) {
        this.idenFaseCampeonato = idenFaseCampeonato;
    }

    public Long getIdenCriterioDesempate() {
        return idenCriterioDesempate;
    }

    public void setIdenCriterioDesempate(Long idenCriterioDesempate) {
        this.idenCriterioDesempate = idenCriterioDesempate;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
