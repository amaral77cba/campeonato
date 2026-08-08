package com.amarildo.campeonato.dto;

import com.amarildo.campeonato.entity.enums.StatusTipoDisputa;

public class TipoDisputaRequestDTO {

    private String nomeTipoDisputa;
    private String statusTipoDisputa;

    public TipoDisputaRequestDTO() {
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
