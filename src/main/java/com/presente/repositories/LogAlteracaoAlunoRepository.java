package com.presente.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.presente.domains.LogAlteracaoAluno;

@Repository
public interface LogAlteracaoAlunoRepository extends JpaRepository<LogAlteracaoAluno, Integer> {

	Optional<LogAlteracaoAluno> findFirstByMatriculaAndAnoLetivoAndAtivoOrderByDataUltimaAtualizacaoDesc(
			String matricula, Integer anoLetivo, Boolean ativo);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE LogAlteracaoAluno SET foto = true WHERE id = :id")
	void updateHasFoto(@Param("id") Integer id);

}
