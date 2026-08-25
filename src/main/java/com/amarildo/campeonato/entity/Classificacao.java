package com.amarildo.campeonato.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "classificacao",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_classificacao_fase_campeonatoequipe",
                columnNames = {"iden_fasecampeonato", "iden_campeonatoequipe"}
        ))
public class Classificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_classificacao")
    private Long idenClassificacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_fasecampeonato", nullable = false)
    private FaseCampeonato faseCampeonato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_campeonatoequipe", nullable = false)
    private CampeonatoEquipe campeonatoEquipe;

    @Column(name = "posicao")
    private Integer posicao;

    @Column(name = "pontos", nullable = false)
    private Integer pontos;

    @Column(name = "jogos", nullable = false)
    private Integer jogos;

    @Column(name = "vitorias", nullable = false)
    private Integer vitorias;

    @Column(name = "empates", nullable = false)
    private Integer empates;

    @Column(name = "derrotas", nullable = false)
    private Integer derrotas;

    @Column(name = "gols_pro", nullable = false)
    private Integer golsPro;

    @Column(name = "gols_contra", nullable = false)
    private Integer golsContra;

    @Column(name = "saldo_gols", insertable = false, updatable = false)
    private Integer saldoGols;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "ultimos_jogos", length = 5)
    private String ultimosJogos;

    public Classificacao() {
    }

    public Long getIdenClassificacao() {
        return idenClassificacao;
    }

    public void setIdenClassificacao(Long idenClassificacao) {
        this.idenClassificacao = idenClassificacao;
    }

    public FaseCampeonato getFaseCampeonato() {
        return faseCampeonato;
    }

    public void setFaseCampeonato(FaseCampeonato faseCampeonato) {
        this.faseCampeonato = faseCampeonato;
    }

    public CampeonatoEquipe getCampeonatoEquipe() {
        return campeonatoEquipe;
    }

    public void setCampeonatoEquipe(CampeonatoEquipe campeonatoEquipe) {
        this.campeonatoEquipe = campeonatoEquipe;
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
