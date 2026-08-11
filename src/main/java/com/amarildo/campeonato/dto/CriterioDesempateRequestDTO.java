package com.amarildo.campeonato.dto;

public class CriterioDesempateRequestDTO {

    private String nomeCriterioDesempate;
    private String codigoCriterioDesempate;
    private String sentidoOrdenacao;
    private Boolean calculoAutomatico;

    public CriterioDesempateRequestDTO() {
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
}
