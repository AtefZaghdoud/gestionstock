package com.Atef.gestionstock.service;

import java.util.List;
import java.util.Optional;

import com.Atef.gestionstock.dto.UtilisateurDto;
import com.Atef.gestionstock.model.Utilisateur;

public interface UtilisateurService {

	UtilisateurDto save(UtilisateurDto dto);

	UtilisateurDto findById(Integer id);

	UtilisateurDto findUtilisateurByEmail(String email);

	List<UtilisateurDto> findAll();

	void delete(Integer id);

}
