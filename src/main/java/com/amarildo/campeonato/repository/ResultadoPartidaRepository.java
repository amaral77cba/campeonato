package com.amarildo.campeonato.repository;

import com.amarildo.campeonato.entity.ResultadoPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoPartidaRepository extends JpaRepository<ResultadoPartida, Long> {

    boolean existsByPartida_IdenPartida(Long idenPartida);

    boolean existsByPartida_IdenPartidaAndIdenResultadoPartidaNot(
            Long idenPartida,
            Long idenResultadoPartida
    );
}
