package com.presente.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.presente.backend.domains.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

}
