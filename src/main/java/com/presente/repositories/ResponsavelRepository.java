package com.presente.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {

	Optional<Responsavel> findByEmail(String email);

	Optional<Responsavel> findByEmail2(String email);

	Optional<Responsavel> findByEmailAndIdNot(String email, Integer id);
	
	Optional<Responsavel> findByEmail2AndIdNot(String email, Integer id);

}
