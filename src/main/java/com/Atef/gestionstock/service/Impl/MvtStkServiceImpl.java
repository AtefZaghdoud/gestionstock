package com.Atef.gestionstock.service.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.Atef.gestionstock.model.TypeMvtStk;
import com.Atef.gestionstock.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Atef.gestionstock.dto.MvtStkDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.repository.MvtStkRepository;
import com.Atef.gestionstock.service.MvtStkService;
import com.Atef.gestionstock.validator.MvtStkValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {

	
	private MvtStkRepository mvtStkRepository;

	private ArticleService articleService;

	@Autowired
	public MvtStkServiceImpl(MvtStkRepository mvtStkRepository, ArticleService articleService) {
		this.mvtStkRepository = mvtStkRepository;
		this.articleService = articleService;
	}

	@Override
	public BigDecimal stockReelArticle(Integer idArticle) {
		if(idArticle == null){
			log.warn("Id article is null ");
			return BigDecimal.valueOf(-1);
		}
		articleService.findById(idArticle);
		return mvtStkRepository.stockReelArticle(idArticle);
	}

	@Override
	public List<MvtStkDto> mvtStkArticle(Integer idArticle) {
		return mvtStkRepository.findAllByArticleId(idArticle).stream()
				.map(MvtStkDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public MvtStkDto entreeStock(MvtStkDto dto) {
		List<String> errors = MvtStkValidator.validate(dto);
		if (!errors.isEmpty()){
			log.error("mvt de stock is not valid",dto);
			throw new InvalidEntityException("le mvt de stock n'est pas valide ",ErrorCodes.MVT_STK_NOT_VALID,errors);

		}
		dto.setQuantite(
				BigDecimal.valueOf(
						Math.abs(dto.getQuantite().doubleValue())
				)
		);
		dto.setTypeMvt(TypeMvtStk.ENTREE);
		return MvtStkDto.fromEntity(
				mvtStkRepository.save(MvtStkDto.toEntity(dto))
		);
	}

	@Override
	public MvtStkDto sortieStock(MvtStkDto dto) {
		List<String> errors = MvtStkValidator.validate(dto);
		if (!errors.isEmpty()){
			log.error("mvt de stock is not valid",dto);
			throw new InvalidEntityException("le mvt de stock n'est pas valide ",ErrorCodes.MVT_STK_NOT_VALID,errors);

		}
		dto.setQuantite(
				BigDecimal.valueOf(
						Math.abs(dto.getQuantite().doubleValue()) * -1
				)
		);
		dto.setTypeMvt(TypeMvtStk.SORTIE);
		return MvtStkDto.fromEntity(
				mvtStkRepository.save(MvtStkDto.toEntity(dto))
		);
	}

	@Override
	public MvtStkDto correctionStockPos(MvtStkDto dto) {
		List<String> errors = MvtStkValidator.validate(dto);
		if (!errors.isEmpty()){
			log.error("mvt de stock is not valid",dto);
			throw new InvalidEntityException("le mvt de stock n'est pas valide ",ErrorCodes.MVT_STK_NOT_VALID,errors);

		}
		dto.setQuantite(
				BigDecimal.valueOf(
						Math.abs(dto.getQuantite().doubleValue()) * -1
				)
		);
		dto.setTypeMvt(TypeMvtStk.CORRECTION_POS);
		return MvtStkDto.fromEntity(
				mvtStkRepository.save(MvtStkDto.toEntity(dto))
		);
	}

	@Override
	public MvtStkDto correctionStockNeg(MvtStkDto dto) {
		List<String> errors = MvtStkValidator.validate(dto);
		if (!errors.isEmpty()){
			log.error("mvt de stock is not valid",dto);
			throw new InvalidEntityException("le mvt de stock n'est pas valide ",ErrorCodes.MVT_STK_NOT_VALID,errors);

		}
		dto.setQuantite(
				BigDecimal.valueOf(
						Math.abs(dto.getQuantite().doubleValue()) * -1
				)
		);
		dto.setTypeMvt(TypeMvtStk.CORRECTION_NEG);
		return MvtStkDto.fromEntity(
				mvtStkRepository.save(MvtStkDto.toEntity(dto))
		);
	}


	//	@Override
//	public MvtStkDto findById(Integer id) {
//		if (id == null) {
//			log.error("Mvt Stk id is null");
//			return null;
//		}
//
//		return mvtStkRepository.findById(id).map(MvtStkDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
//				"Aucun MVT_STK avec l'ID " + id + " n'ete trouve dans la BDD ", ErrorCodes.MVT_STK_NOT_FOUND));
//	}

//	@Override
//	public List<MvtStkDto> findAll() {
//		return mvtStkRepository.findAll().stream().map(MvtStkDto::fromEntity).collect(Collectors.toList());
//	}

//	@Override
//	public void delete(Integer id) {
//		if (id == null) {
//			log.error("Mvt stk id is null");
//			return;
//		}
//		mvtStkRepository.deleteById(id);
//
//	}

}
