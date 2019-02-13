package com.presente.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.Aluno;
import com.presente.domains.Responsavel;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
 
	Optional<Aluno> findByMatriculaAndAnoLetivoAndAtivo(String matricula, Integer anoLetivo, boolean ativo);

	Optional<Aluno> findByNomeAndAnoLetivoAndAtivo(String nome, Integer anoLetivo, boolean ativo);

	Optional<Aluno> findByNomeAndAnoLetivoAndAtivoAndIdNot(String nome, Integer anoLetivo, boolean ativo, Integer id);

	Optional<Aluno> findByMatriculaAndAnoLetivoAndAtivoAndIdNot(String matricula, Integer anoLetivo, boolean ativo, Integer id);

	long countByResponsavel(Responsavel responsavel);

}
