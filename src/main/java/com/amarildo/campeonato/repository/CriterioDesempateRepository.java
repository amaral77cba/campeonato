package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.CriterioDesempate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioDesempateRepository extends JpaRepository<CriterioDesempate, Long> {

    boolean existsByNomeCriterioDesempateIgnoreCase(String nomeCriterioDesempate);

    boolean existsByNomeCriterioDesempateIgnoreCaseAndIdenCriterioDesempateNot(
            String nomeCriterioDesempate,
            Long idenCriterioDesempate
    );

    boolean existsByCodigoCriterioDesempateIgnoreCase(String codigoCriterioDesempate);

    boolean existsByCodigoCriterioDesempateIgnoreCaseAndIdenCriterioDesempateNot(
            String codigoCriterioDesempate,
            Long idenCriterioDesempate
    );
}
