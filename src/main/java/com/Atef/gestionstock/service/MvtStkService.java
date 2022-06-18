package com.Atef.gestionstock.service;

import java.math.BigDecimal;
import java.util.List;

import com.Atef.gestionstock.dto.MvtStkDto;
import io.swagger.models.auth.In;

public interface MvtStkService {

	BigDecimal stockReelArticle(Integer idArticle);

	List<MvtStkDto> mvtStkArticle(Integer idArticle);

	MvtStkDto entreeStock(MvtStkDto dto);

	MvtStkDto sortieStock(MvtStkDto dto);

	MvtStkDto correctionStockPos(MvtStkDto dto);

	MvtStkDto correctionStockNeg(MvtStkDto dto);

//	MvtStkDto save(MvtStkDto dto);
//
//	MvtStkDto findById(Integer id);
//
//	List<MvtStkDto> findAll();
//
//	void delete(Integer id);
}
