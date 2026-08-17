package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.FaixaClassificacaoVigencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FaixaClassificacaoVigenciaRepository extends JpaRepository<FaixaClassificacaoVigencia, Long> {

    boolean existsByFaixaClassificacao_IdenFaixaClassificacaoAndDataInicioVigencia(
            Long idenFaixaClassificacao,
            LocalDate dataInicioVigencia
    );

    boolean existsByFaixaClassificacao_IdenFaixaClassificacaoAndDataInicioVigenciaAndIdenFaixaClassificacaoVigenciaNot(
            Long idenFaixaClassificacao,
            LocalDate dataInicioVigencia,
            Long idenFaixaClassificacaoVigencia
    );
}
