package com.presente.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {

	Optional<Responsavel> findByEmailAndAtivo(String email, boolean ativo);

	Optional<Responsavel> findByEmail2AndAtivo(String email, boolean ativo);

	Optional<Responsavel> findByEmailAndAtivoAndIdNot(String email, boolean ativo, Integer id);
	
	Optional<Responsavel> findByEmail2AndAtivoAndIdNot(String email, boolean ativo, Integer id);

	Optional<Responsavel> findByCpfAndAtivo(String cpf, boolean ativo);
	
	Optional<Responsavel> findByCpfAndAtivoAndIdNot(String cpf, boolean ativo, Integer id);

	Optional<Responsavel> findByNomeAndAtivoAndIdNot(String nome, boolean ativo, Integer id); 
	
	Optional<Responsavel> findByNomeAndAtivo(String nome, boolean ativo);

	Page<Responsavel> findAllByAtivoAndNomeContainsOrCpfContainsOrEmailContainsOrEmail2Contains(boolean ativo, String nome, String cpf, String email, String email2,
			Pageable pageable);

}
