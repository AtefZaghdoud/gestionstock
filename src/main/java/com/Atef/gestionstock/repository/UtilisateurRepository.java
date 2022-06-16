package com.Atef.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Atef.gestionstock.model.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer >{

    Optional<Utilisateur> findUtilisateurByEmail(@Param("email") String email);

}
