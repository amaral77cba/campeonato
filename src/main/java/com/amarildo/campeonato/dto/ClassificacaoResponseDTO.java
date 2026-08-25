package com.amarildo.campeonato.dto;

import java.time.LocalDateTime;

public class ClassificacaoResponseDTO {

    private Long idenClassificacao;
    private Long idenFaseCampeonato;
    private String nomeFaseCampeonato;
    private Long idenCampeonatoEquipe;
    private String nomeEquipe;
    private Integer posicao;
    private Integer pontos;
    private Integer jogos;
    private Integer vitorias;
    private Integer empates;
    private Integer derrotas;
    private Integer golsPro;
    private Integer golsContra;
    private Integer saldoGols;
    private LocalDateTime dataAtualizacao;
    private String ultimosJogos;

    public ClassificacaoResponseDTO() {
    }

    public Long getIdenClassificacao() {
        return idenClassificacao;
    }

    public void setIdenClassificacao(Long idenClassificacao) {
        this.idenClassificacao = idenClassificacao;
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

    public Long getIdenCampeonatoEquipe() {
        return idenCampeonatoEquipe;
    }

    public void setIdenCampeonatoEquipe(Long idenCampeonatoEquipe) {
        this.idenCampeonatoEquipe = idenCampeonatoEquipe;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Integer getJogos() {
        return jogos;
    }

    public void setJogos(Integer jogos) {
        this.jogos = jogos;
    }

    public Integer getVitorias() {
        return vitorias;
    }

    public void setVitorias(Integer vitorias) {
        this.vitorias = vitorias;
    }

    public Integer getEmpates() {
        return empates;
    }

    public void setEmpates(Integer empates) {
        this.empates = empates;
    }

    public Integer getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(Integer derrotas) {
        this.derrotas = derrotas;
    }

    public Integer getGolsPro() {
        return golsPro;
    }

    public void setGolsPro(Integer golsPro) {
        this.golsPro = golsPro;
    }

    public Integer getGolsContra() {
        return golsContra;
    }

    public void setGolsContra(Integer golsContra) {
        this.golsContra = golsContra;
    }

    public Integer getSaldoGols() {
        return saldoGols;
    }

    public void setSaldoGols(Integer saldoGols) {
        this.saldoGols = saldoGols;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getUltimosJogos() {
        return ultimosJogos;
    }

    public void setUltimosJogos(String ultimosJogos) {
        this.ultimosJogos = ultimosJogos;
    }
}
