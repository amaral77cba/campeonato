package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.CriterioDesempate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioDesempateRepository extends JpaRepository<CriterioDesempate, Long> {
}
