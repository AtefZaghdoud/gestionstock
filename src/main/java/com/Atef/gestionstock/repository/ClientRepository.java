package com.Atef.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Atef.gestionstock.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
