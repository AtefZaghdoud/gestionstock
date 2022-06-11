package com.Atef.gestionstock.controller;

import com.Atef.gestionstock.controller.api.VentesApi;
import com.Atef.gestionstock.dto.VenteDto;
import com.Atef.gestionstock.service.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteController implements VentesApi {

    private VentesService ventesService;

    @Autowired
    public VenteController(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        return ventesService.save(dto);
    }

    @Override
    public VenteDto findById(Integer id) {
        return ventesService.findById(id);
    }

    @Override
    public VenteDto findByCode(String code) {
        return ventesService.findByCode(code);
    }

    @Override
    public List<VenteDto> findAll() {
        return ventesService.findAll();
    }

    @Override
    public void delete(Integer id) {
        ventesService.delete(id);
    }
}
