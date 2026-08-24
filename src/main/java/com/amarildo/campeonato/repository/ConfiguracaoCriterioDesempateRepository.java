package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.ConfiguracaoCriterioDesempate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracaoCriterioDesempateRepository extends JpaRepository<ConfiguracaoCriterioDesempate, Long> {

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndCriterioDesempate_IdenCriterioDesempate(
            Long idenFaseCampeonato,
            Long idenCriterioDesempate
    );

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndCriterioDesempate_IdenCriterioDesempateAndIdenConfigCriterioDesempateNot(
            Long idenFaseCampeonato,
            Long idenCriterioDesempate,
            Long idenConfigCriterioDesempate
    );

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndOrdem(
            Long idenFaseCampeonato,
            Integer ordem
    );

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndOrdemAndIdenConfigCriterioDesempateNot(
            Long idenFaseCampeonato,
            Integer ordem,
            Long idenConfigCriterioDesempate
    );
}
