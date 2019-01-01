package com.presente.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.backend.domains.HistoricoAlteracaoAluno;

@Repository
public interface HistoricoAlteracaoAlunoRepository extends JpaRepository<HistoricoAlteracaoAluno, Integer> {

}
