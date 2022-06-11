package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.EntrepriseDto;


public interface EntrepriseService {
	
	EntrepriseDto save(EntrepriseDto dto);
	
	EntrepriseDto findById(Integer id); 
	
	List<EntrepriseDto> findAll();
	
	void delete(Integer id);

}
