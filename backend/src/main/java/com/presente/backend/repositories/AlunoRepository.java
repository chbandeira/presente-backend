package com.presente.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.presente.backend.domains.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
 
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	Optional<Aluno> findByMatriculaAndAnoLetivoAndIdNot(String matricula, Integer anoLetivo, Integer id);

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	Optional<Aluno> findByMatriculaAndAnoLetivo(String matricula, Integer anoLetivo);

}
