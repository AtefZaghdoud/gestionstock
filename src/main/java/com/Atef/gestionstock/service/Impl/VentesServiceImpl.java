package com.Atef.gestionstock.service.Impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.dto.MvtStkDto;
import com.Atef.gestionstock.exception.InvalidOperationException;
import com.Atef.gestionstock.model.*;
import com.Atef.gestionstock.service.MvtStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.Atef.gestionstock.dto.LigneVenteDto;
import com.Atef.gestionstock.dto.VenteDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.repository.ArticleRepository;
import com.Atef.gestionstock.repository.LigneVenteRepository;
import com.Atef.gestionstock.repository.VenteRepository;
import com.Atef.gestionstock.service.VentesService;
import com.Atef.gestionstock.validator.VenteValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VentesServiceImpl implements  VentesService{

	private ArticleRepository articleRepository;
	private VenteRepository venteRepository;
	private LigneVenteRepository ligneVenteRepository;
	private MvtStkService mvtStkService;
	
	@Autowired
	public VentesServiceImpl(ArticleRepository articleRepository, VenteRepository venteRepository,
							 LigneVenteRepository ligneVenteRepository, MvtStkService mvtStkService) {
		super();
		this.articleRepository = articleRepository;
		this.venteRepository = venteRepository;
		this.ligneVenteRepository = ligneVenteRepository;
		this.mvtStkService = mvtStkService;
	}

	@Override
	public VenteDto save(VenteDto dto) {
		List<String> errors = VenteValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Ventes n'est pas valide ");
			throw new InvalidEntityException("L'objet Vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
		}
		
		List<String> articleErrors = new ArrayList<>();
		
		dto.getLigneVentes().forEach(ligneVenteDto -> {
			Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
			if (article.isEmpty()) {
				articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId() + "n'a ete trouve dans la BDD" );
			}
		});
		
		if (!articleErrors.isEmpty()) {
			log.error("one or more articles were not found in DB , {} ",errors);
			throw new InvalidEntityException("Un ou plusieurs article n'ont pas ete trouve dans la BDD ", ErrorCodes.VENTE_NOT_FOUND, errors);
		}
		
		Ventes savedVentes = venteRepository.save(VenteDto.toEntity(dto));
		dto.getLigneVentes().forEach(ligneVenteDto -> {
			LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
			ligneVente.setVente(savedVentes);
			ligneVenteRepository.save(ligneVente);
			updateMvtStk(ligneVente);
		});
		
		return VenteDto.fromEntity(savedVentes);
	}

	@Override
	public VenteDto findById(Integer id) {
		if (id == null) {
			log.error("Ventes ID is null");
			return null;
		}
		
		return venteRepository.findById(id)
				.map(VenteDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun Vente n'a ete trouve avec l'ID "+ id , ErrorCodes.VENTE_NOT_FOUND));
	}

	@Override
	public VenteDto findByCode(String code) {
		if (!StringUtils.hasLength(code)) {
			log.error("Vente Code is null");
			return null;
		}
		return venteRepository.findVentesByCode(code)
				.map(VenteDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune vente ,n'a ete trouve avec l'Code "+ code , ErrorCodes.VENTE_NOT_FOUND));
	}

	@Override
	public List<VenteDto> findAll() {
		return venteRepository.findAll().stream()
				.map(VenteDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Vente ID is null");
			return ;
		}
		List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
		if (!ligneVentes.isEmpty()){
			throw new InvalidOperationException("Impossible de supprimer un ligne vente qui est déja utilisé ",ErrorCodes.VENTE_ALREADY_IN_USE);
		}
		venteRepository.deleteById(id);
		
	}

	private void updateMvtStk(LigneVente lig){
			MvtStkDto mvtStkDto = MvtStkDto.builder()
					.article(ArticleDto.fromEntity(lig.getArticle()))
					.dateMvt(Instant.now())
					.typeMvt(TypeMvtStk.SORTIE)
					.sourceMvtStk(SourceMvtStk.VENTE)
					.quantite(lig.getQuantite())
					.idEntreprise(lig.getIdEntreprise())
					.build();
			mvtStkService.sortieStock(mvtStkDto);

	}


}
