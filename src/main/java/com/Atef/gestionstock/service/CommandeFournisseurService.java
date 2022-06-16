package com.Atef.gestionstock.service;

import java.math.BigDecimal;
import java.util.List;

import com.Atef.gestionstock.dto.CommandeFournisseurDto;
import com.Atef.gestionstock.dto.CommandeFournisseurDto;
import com.Atef.gestionstock.model.EtatCommande;

public interface CommandeFournisseurService {

	CommandeFournisseurDto save(CommandeFournisseurDto dto);

	CommandeFournisseurDto findById(Integer id);
	
	CommandeFournisseurDto findByCode(String code);

	List<CommandeFournisseurDto> findAll();
	
	void delete(Integer id);
}
