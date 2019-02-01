package com.presente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

}
