package com.amarildo.campeonato.dto;

public class FaseCampeonatoRequestDTO {

    private Long idenCampeonato;
    private String nomeFaseCampeonato;
    private Integer ordem;
    private Integer qtdClassificam;
    private Boolean possuiIdaVolta;
    private Long idenTipoDisputa;

    public FaseCampeonatoRequestDTO() {
    }

    public Long getIdenCampeonato() {
        return idenCampeonato;
    }

    public void setIdenCampeonato(Long idenCampeonato) {
        this.idenCampeonato = idenCampeonato;
    }

    public String getNomeFaseCampeonato() {
        return nomeFaseCampeonato;
    }

    public void setNomeFaseCampeonato(String nomeFaseCampeonato) {
        this.nomeFaseCampeonato = nomeFaseCampeonato;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Integer getQtdClassificam() {
        return qtdClassificam;
    }

    public void setQtdClassificam(Integer qtdClassificam) {
        this.qtdClassificam = qtdClassificam;
    }

    public Boolean getPossuiIdaVolta() {
        return possuiIdaVolta;
    }

    public void setPossuiIdaVolta(Boolean possuiIdaVolta) {
        this.possuiIdaVolta = possuiIdaVolta;
    }

    public Long getIdenTipoDisputa() {
        return idenTipoDisputa;
    }

    public void setIdenTipoDisputa(Long idenTipoDisputa) {
        this.idenTipoDisputa = idenTipoDisputa;
    }
}
