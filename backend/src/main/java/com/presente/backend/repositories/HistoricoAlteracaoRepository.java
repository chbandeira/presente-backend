package com.presente.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.backend.domains.HistoricoAlteracao;

@Repository
public interface HistoricoAlteracaoRepository extends JpaRepository<HistoricoAlteracao, Long> {

}
