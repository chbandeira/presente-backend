package com.presente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {

}
