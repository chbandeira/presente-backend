package com.presente.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.presente.backend.domains.Matricula;

/**
 * @author Charlles Bandeira
 *
 */
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

}
