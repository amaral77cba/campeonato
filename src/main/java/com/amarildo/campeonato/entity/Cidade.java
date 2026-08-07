package com.amarildo.campeonato.entity;

import com.amarildo.campeonato.entity.enums.Uf;
import jakarta.persistence.*;


@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_cidade")
    private Long idenCidade;

    @Column(name = "nome_cidade", nullable = false, length = 200)
    private String nomeCidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "uf", nullable = false, length = 2)
    private Uf uf;

    public Cidade() {
    }

    public Long getIdenCidade() {
        return idenCidade;
    }

    public void setIdenCidade(Long idenCidade) {
        this.idenCidade = idenCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }
}
