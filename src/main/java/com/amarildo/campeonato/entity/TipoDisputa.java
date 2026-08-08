package com.amarildo.campeonato.entity;

import com.amarildo.campeonato.entity.enums.StatusTipoDisputa;
import jakarta.persistence.*;

@Entity
@Table(name = "tipodisputa")
public class TipoDisputa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_tipodisputa")
    private Long idenTipoDisputa;

    @Column(name = "nome_tipodisputa", nullable = false, length = 100)
    private String nomeTipoDisputa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_tipodisputa", nullable = false, length = 20)
    private StatusTipoDisputa statusTipoDisputa;

    public TipoDisputa() {
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

    public StatusTipoDisputa getStatusTipoDisputa() {
        return statusTipoDisputa;
    }

    public void setStatusTipoDisputa(StatusTipoDisputa statusTipoDisputa) {
        this.statusTipoDisputa = statusTipoDisputa;
    }
}
