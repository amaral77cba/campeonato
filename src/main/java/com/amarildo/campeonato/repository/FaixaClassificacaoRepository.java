package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.FaixaClassificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaixaClassificacaoRepository extends JpaRepository<FaixaClassificacao, Long> {

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndNomeFaixa(Long idenFaseCampeonato, String nomeFaixa);

    boolean existsByFaseCampeonato_IdenFaseCampeonatoAndNomeFaixaAndIdenFaixaClassificacaoNot(
            Long idenFaseCampeonato,
            String nomeFaixa,
            Long idenFaixaClassificacao
    );
}
