package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.TipoDisputa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDisputaRepository extends JpaRepository<TipoDisputa, Long>{
}
