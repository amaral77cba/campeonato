package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.CampeonatoEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoEquipeRepository extends JpaRepository<CampeonatoEquipe, Long> {

    boolean existsByCampeonato_IdenCampeonatoAndEquipe_IdenEquipe(Long idenCampeonato, Long idenEquipe);

    boolean existsByCampeonato_IdenCampeonatoAndEquipe_IdenEquipeAndIdenCampeonatoEquipeNot(
            Long idenCampeonato,
            Long idenEquipe,
            Long idenCampeonatoEquipe
    );
}
