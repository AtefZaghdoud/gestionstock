package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.FournisseurDto;


public interface FournisseurService {

	FournisseurDto save(FournisseurDto dto);
	
	FournisseurDto findById(Integer id); 
	
	List<FournisseurDto> findAll();
	
	void delete(Integer id);

}
