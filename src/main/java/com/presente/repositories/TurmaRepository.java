package com.presente.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

	Page<Turma> findAllByAtivoAndDescricaoOrSerieOrSala(boolean ativo, String descricao, String serie, String sala, Pageable pageable);

}
