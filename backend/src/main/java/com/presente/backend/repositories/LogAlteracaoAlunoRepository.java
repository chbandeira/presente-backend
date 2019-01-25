package com.presente.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.presente.backend.domains.LogAlteracaoAluno;

@Repository
public interface LogAlteracaoAlunoRepository extends JpaRepository<LogAlteracaoAluno, Integer> {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	Optional<LogAlteracaoAluno> findFirstByMatriculaAndAnoLetivoAndAtivoOrderByDataUltimaAtualizacaoDesc(
			String matricula, Integer anoLetivo, Boolean ativo);

}
