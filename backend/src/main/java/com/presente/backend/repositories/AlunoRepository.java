package com.presente.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.backend.domains.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
 
	Optional<Aluno> findByMatriculaAndAnoLetivoAndIdNot(String matricula, Integer anoLetivo, Integer id);

	Optional<Aluno> findByMatriculaAndAnoLetivo(String matricula, Integer anoLetivo);

}
