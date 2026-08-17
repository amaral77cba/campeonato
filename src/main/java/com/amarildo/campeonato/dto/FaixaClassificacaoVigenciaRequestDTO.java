package com.amarildo.campeonato.dto;

import java.time.LocalDate;

public class FaixaClassificacaoVigenciaRequestDTO {

    private Long idenFaixaClassificacao;
    private Integer posicaoInicial;
    private Integer posicaoFinal;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;
    private String motivoAlteracao;

    public FaixaClassificacaoVigenciaRequestDTO() {
    }

    public Long getIdenFaixaClassificacao() {
        return idenFaixaClassificacao;
    }

    public void setIdenFaixaClassificacao(Long idenFaixaClassificacao) {
        this.idenFaixaClassificacao = idenFaixaClassificacao;
    }

    public Integer getPosicaoInicial() {
        return posicaoInicial;
    }

    public void setPosicaoInicial(Integer posicaoInicial) {
        this.posicaoInicial = posicaoInicial;
    }

    public Integer getPosicaoFinal() {
        return posicaoFinal;
    }

    public void setPosicaoFinal(Integer posicaoFinal) {
        this.posicaoFinal = posicaoFinal;
    }

    public LocalDate getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public LocalDate getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(LocalDate dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public String getMotivoAlteracao() {
        return motivoAlteracao;
    }

    public void setMotivoAlteracao(String motivoAlteracao) {
        this.motivoAlteracao = motivoAlteracao;
    }
}
