package com.amarildo.campeonato.dto;

public class TipoDisputaResponseDTO {

    private Long idenTipoDisputa;
    private String nomeTipoDisputa;
    private String statusTipoDisputa;

    public TipoDisputaResponseDTO() {
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

    public String getStatusTipoDisputa() {
        return statusTipoDisputa;
    }

    public void setStatusTipoDisputa(String statusTipoDisputa) {
        this.statusTipoDisputa = statusTipoDisputa;
    }
}
