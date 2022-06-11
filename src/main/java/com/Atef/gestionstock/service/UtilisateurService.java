package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.UtilisateurDto;

public interface UtilisateurService {

	UtilisateurDto save(UtilisateurDto dto);

	UtilisateurDto findById(Integer id);

	List<UtilisateurDto> findAll();

	void delete(Integer id);

}
