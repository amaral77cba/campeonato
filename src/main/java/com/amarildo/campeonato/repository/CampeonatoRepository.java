package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CampeonatoRepository extends JpaRepository<Campeonato, Long>{
}
