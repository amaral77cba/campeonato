package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.Classificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificacaoRepository extends JpaRepository<Classificacao, Long> {

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndCampeonatoEquipe_IdenCampeonatoEquipe(
            Long idenFaseCampeonato,
            Long idenCampeonatoEquipe
    );

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndCampeonatoEquipe_IdenCampeonatoEquipeAndIdenClassificacaoNot(
            Long idenFaseCampeonato,
            Long idenCampeonatoEquipe,
            Long idenClassificacao
    );
}
