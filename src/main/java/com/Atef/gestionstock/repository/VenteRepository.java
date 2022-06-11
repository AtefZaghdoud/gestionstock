package com.Atef.gestionstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Atef.gestionstock.model.Ventes;

public interface VenteRepository extends JpaRepository<Ventes, Integer> {
	Optional<Ventes> findVentesByCode(String code);

}
