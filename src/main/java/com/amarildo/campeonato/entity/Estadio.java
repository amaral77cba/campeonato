package com.amarildo.campeonato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estadio")
public class Estadio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_estadio")
    private Long idenEstadio;

    @Column(name = "nome_estadio", nullable = false, length = 200)
    private String nomeEstadio;

    @Column(name = "apelido_estadio", length = 200)
    private String apelidoEstadio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iden_cidade", nullable = false)
    private Cidade cidade;

    public Estadio() {
    }

    public Long getIdenEstadio() {
        return idenEstadio;
    }

    public void setIdenEstadio(Long idenEstadio) {
        this.idenEstadio = idenEstadio;
    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }

    public String getApelidoEstadio() {
        return apelidoEstadio;
    }

    public void setApelidoEstadio(String apelidoEstadio) {
        this.apelidoEstadio = apelidoEstadio;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}