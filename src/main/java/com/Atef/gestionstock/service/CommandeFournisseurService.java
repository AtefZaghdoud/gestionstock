package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.CommandeFournisseurDto;

public interface CommandeFournisseurService {

	CommandeFournisseurDto save(CommandeFournisseurDto dto);
	
	CommandeFournisseurDto findById(Integer id);
	
	CommandeFournisseurDto findByCode(String code);

	List<CommandeFournisseurDto> findAll();
	
	void delete(Integer id);
}
