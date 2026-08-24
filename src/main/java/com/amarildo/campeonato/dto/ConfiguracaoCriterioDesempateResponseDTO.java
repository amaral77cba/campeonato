package com.amarildo.campeonato.dto;

public class ConfiguracaoCriterioDesempateResponseDTO {

    private Long idenConfigCriterioDesempate;
    private Long idenFaseCampeonato;
    private String nomeFaseCampeonato;
    private Long idenCriterioDesempate;
    private String nomeCriterioDesempate;
    private String codigoCriterioDesempate;
    private String sentidoOrdenacao;
    private Boolean calculoAutomatico;
    private Integer ordem;

    public ConfiguracaoCriterioDesempateResponseDTO() {
    }

    public Long getIdenConfigCriterioDesempate() {
        return idenConfigCriterioDesempate;
    }

    public void setIdenConfigCriterioDesempate(Long idenConfigCriterioDesempate) {
        this.idenConfigCriterioDesempate = idenConfigCriterioDesempate;
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

    public Long getIdenCriterioDesempate() {
        return idenCriterioDesempate;
    }

    public void setIdenCriterioDesempate(Long idenCriterioDesempate) {
        this.idenCriterioDesempate = idenCriterioDesempate;
    }

    public String getNomeCriterioDesempate() {
        return nomeCriterioDesempate;
    }

    public void setNomeCriterioDesempate(String nomeCriterioDesempate) {
        this.nomeCriterioDesempate = nomeCriterioDesempate;
    }

    public String getCodigoCriterioDesempate() {
        return codigoCriterioDesempate;
    }

    public void setCodigoCriterioDesempate(String codigoCriterioDesempate) {
        this.codigoCriterioDesempate = codigoCriterioDesempate;
    }

    public String getSentidoOrdenacao() {
        return sentidoOrdenacao;
    }

    public void setSentidoOrdenacao(String sentidoOrdenacao) {
        this.sentidoOrdenacao = sentidoOrdenacao;
    }

    public Boolean getCalculoAutomatico() {
        return calculoAutomatico;
    }

    public void setCalculoAutomatico(Boolean calculoAutomatico) {
        this.calculoAutomatico = calculoAutomatico;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
