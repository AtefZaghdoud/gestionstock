package com.Atef.gestionstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Atef.gestionstock.model.CommandeClient;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {

	Optional<CommandeClient> findCommandeClientByCode(String code);
}
