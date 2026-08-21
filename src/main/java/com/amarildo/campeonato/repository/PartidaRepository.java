package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

    boolean existsByRodada_IdenRodadaAndCampeonatoEquipeMandante_IdenCampeonatoEquipeAndCampeonatoEquipeVisitante_IdenCampeonatoEquipe(
            Long idenRodada,
            Long idenCampeonatoEquipeMandante,
            Long idenCampeonatoEquipeVisitante
    );

    boolean existsByRodada_IdenRodadaAndCampeonatoEquipeMandante_IdenCampeonatoEquipeAndCampeonatoEquipeVisitante_IdenCampeonatoEquipeAndIdenPartidaNot(
            Long idenRodada,
            Long idenCampeonatoEquipeMandante,
            Long idenCampeonatoEquipeVisitante,
            Long idenPartida
    );
}
