package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.FaseCampeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaseCampeonatoRepository extends JpaRepository<FaseCampeonato, Long> {

    boolean existsByCampeonato_IdenCampeonatoAndOrdem(Long idenCampeonato, Integer ordem);

    boolean existsByCampeonato_IdenCampeonatoAndOrdemAndIdenFaseCampeonatoNot(
            Long idenCampeonato,
            Integer ordem,
            Long idenFaseCampeonato
    );
}
