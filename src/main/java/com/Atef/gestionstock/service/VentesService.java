package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.VenteDto;

public interface VentesService {
	VenteDto save(VenteDto dto);
	
	VenteDto findById(Integer id);
	
	VenteDto findByCode(String Code);

	List<VenteDto> findAll();
	
	void delete(Integer id);
}
