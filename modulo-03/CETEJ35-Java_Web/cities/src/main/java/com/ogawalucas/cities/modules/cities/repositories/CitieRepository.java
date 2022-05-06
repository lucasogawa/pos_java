package com.ogawalucas.cities.modules.cities.repositories;

import com.ogawalucas.cities.modules.cities.entities.Citie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CitieRepository extends JpaRepository<Citie, Long> {

    Optional<Citie> findByNameAndState(String name, String state);
}
