package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.CommandeClientDto;

public interface CommandeClientService {
	
	CommandeClientDto save(CommandeClientDto dto);
	
	CommandeClientDto findById(Integer id);
	
	CommandeClientDto findByCode(String code);

	List<CommandeClientDto> findAll();
	
	void delete(Integer id);
	

}
