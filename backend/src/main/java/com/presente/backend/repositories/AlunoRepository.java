package com.presente.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.backend.domains.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
