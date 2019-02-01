package com.presente.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
 
	Optional<Aluno> findByMatriculaAndAnoLetivoAndIdNot(String matricula, Integer anoLetivo, Integer id);

	Optional<Aluno> findByMatriculaAndAnoLetivo(String matricula, Integer anoLetivo);

	Optional<Aluno> findByNomeAndAnoLetivoAndIdNot(String nome, Integer anoLetivo, Integer id);

	Optional<Aluno> findByNomeAndAnoLetivo(String nome, Integer anoLetivo);

}
