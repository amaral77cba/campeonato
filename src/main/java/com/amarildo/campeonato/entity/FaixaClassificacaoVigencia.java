package com.amarildo.campeonato.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "faixaclassificacaovigencia",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_faixavigencia_inicio",
                columnNames = {"iden_faixaclassificacao", "data_inicio_vigencia"}
        ))
public class FaixaClassificacaoVigencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_faixaclassificacaovigencia")
    private Long idenFaixaClassificacaoVigencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_faixaclassificacao", nullable = false)
    private FaixaClassificacao faixaClassificacao;

    @Column(name = "posicao_inicial", nullable = false)
    private Integer posicaoInicial;

    @Column(name = "posicao_final", nullable = false)
    private Integer posicaoFinal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_inicio_vigencia", nullable = false)
    private LocalDate dataInicioVigencia;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_fim_vigencia")
    private LocalDate dataFimVigencia;

    @Column(name = "motivo_alteracao", length = 500)
    private String motivoAlteracao;

    public FaixaClassificacaoVigencia() {
    }

    public Long getIdenFaixaClassificacaoVigencia() {
        return idenFaixaClassificacaoVigencia;
    }

    public void setIdenFaixaClassificacaoVigencia(Long idenFaixaClassificacaoVigencia) {
        this.idenFaixaClassificacaoVigencia = idenFaixaClassificacaoVigencia;
    }

    public FaixaClassificacao getFaixaClassificacao() {
        return faixaClassificacao;
    }

    public void setFaixaClassificacao(FaixaClassificacao faixaClassificacao) {
        this.faixaClassificacao = faixaClassificacao;
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
