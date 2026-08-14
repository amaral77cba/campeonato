package com.amarildo.campeonato.dto;

public class FaseCampeonatoResponseDTO {

    private Long idenFaseCampeonato;
    private Long idenCampeonato;
    private String nomeCampeonato;
    private String nomeFaseCampeonato;
    private Integer ordem;
    private Integer qtdClassificam;
    private Boolean possuiIdaVolta;
    private Long idenTipoDisputa;
    private String nomeTipoDisputa;

    public FaseCampeonatoResponseDTO() {
    }

    public Long getIdenFaseCampeonato() {
        return idenFaseCampeonato;
    }

    public void setIdenFaseCampeonato(Long idenFaseCampeonato) {
        this.idenFaseCampeonato = idenFaseCampeonato;
    }

    public Long getIdenCampeonato() {
        return idenCampeonato;
    }

    public void setIdenCampeonato(Long idenCampeonato) {
        this.idenCampeonato = idenCampeonato;
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
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

    public String getNomeTipoDisputa() {
        return nomeTipoDisputa;
    }

    public void setNomeTipoDisputa(String nomeTipoDisputa) {
        this.nomeTipoDisputa = nomeTipoDisputa;
    }
}
