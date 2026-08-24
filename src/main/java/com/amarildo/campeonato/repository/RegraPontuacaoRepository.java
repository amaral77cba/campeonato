package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.RegraPontuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegraPontuacaoRepository extends JpaRepository<RegraPontuacao, Long> {

    boolean existsByFaseCampeonato_IdenFaseCampeonato(Long idenFaseCampeonato);

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndIdenRegraPontuacaoNot(
            Long idenFaseCampeonato,
            Long idenRegraPontuacao
    );
}
