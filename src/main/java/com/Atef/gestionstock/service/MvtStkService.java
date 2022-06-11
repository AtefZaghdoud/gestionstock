package com.Atef.gestionstock.service;

import java.util.List;

import com.Atef.gestionstock.dto.MvtStkDto;

public interface MvtStkService {
	
	MvtStkDto save(MvtStkDto dto);
	
	MvtStkDto findById(Integer id); 
	
	List<MvtStkDto> findAll();
	
	void delete(Integer id);
}
