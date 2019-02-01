package com.presente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.domains.LogEnvioEmail;

@Repository
public interface LogEnvioEmailRepository extends JpaRepository<LogEnvioEmail, Integer> {

}
