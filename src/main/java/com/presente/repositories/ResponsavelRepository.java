package com.presente.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {

	Optional<Responsavel> findByEmailAndAtivo(String email, boolean ativo);

	Optional<Responsavel> findByEmail2AndAtivo(String email, boolean ativo);

	Optional<Responsavel> findByEmailAndAtivoAndIdNot(String email, boolean ativo, Integer id);
	
	Optional<Responsavel> findByEmail2AndAtivoAndIdNot(String email, boolean ativo, Integer id);

}
