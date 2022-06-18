package com.Atef.gestionstock.controller;

import com.Atef.gestionstock.controller.api.MvtStkApi;
import com.Atef.gestionstock.dto.MvtStkDto;
import com.Atef.gestionstock.service.MvtStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MvtStkController implements MvtStkApi {

    private MvtStkService mvtStkService;

    @Autowired
    public MvtStkController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        return mvtStkService.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Integer idArticle) {
        return mvtStkService.mvtStkArticle(idArticle);
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
        return mvtStkService.entreeStock(dto);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {
        return mvtStkService.sortieStock(dto);
    }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto dto) {
        return mvtStkService.correctionStockPos(dto);
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {
        return mvtStkService.correctionStockNeg(dto);
    }

    //    @Override
//    public MvtStkDto save(MvtStkDto dto) {
//        return mvtStkService.save(dto);
//    }
//
//    @Override
//    public MvtStkDto findById(Integer id) {
//        return mvtStkService.findById(id);
//    }
//
//    @Override
//    public List<MvtStkDto> findAll() {
//        return mvtStkService.findAll();
//    }
//
//    @Override
//    public void delete(Integer id) {
//        mvtStkService.delete(id);
//    }
}
