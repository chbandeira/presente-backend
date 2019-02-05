package com.presente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.Telefone;
import com.presente.domains.TelefoneResponsavelPK;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, TelefoneResponsavelPK> {

}
