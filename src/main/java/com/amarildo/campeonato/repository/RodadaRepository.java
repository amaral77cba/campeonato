package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.Rodada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RodadaRepository extends JpaRepository<Rodada, Long> {

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndOrdemRodada(
            Long idenFaseCampeonato,
            Integer ordemRodada
    );

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndOrdemRodadaAndIdenRodadaNot(
            Long idenFaseCampeonato,
            Integer ordemRodada,
            Long idenRodada
    );
}
