package com.presente.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.backend.domains.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

}
