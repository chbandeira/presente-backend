package com.presente.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.LogAlteracaoAluno;

@Repository
public interface LogAlteracaoAlunoRepository extends JpaRepository<LogAlteracaoAluno, Integer> {

	Optional<LogAlteracaoAluno> findFirstByMatriculaAndAnoLetivoAndAtivoOrderByDataUltimaAtualizacaoDesc(
			String matricula, Integer anoLetivo, Boolean ativo);

}
