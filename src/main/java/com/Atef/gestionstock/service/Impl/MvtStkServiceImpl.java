package com.Atef.gestionstock.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

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
	
	@Autowired
	public MvtStkServiceImpl(MvtStkRepository mvtStkRepository) {
		super();
		this.mvtStkRepository = mvtStkRepository;
	}

	@Override
	public MvtStkDto save(MvtStkDto dto) {
		List<String> errors = MvtStkValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Mvt Stk is not Valid {}", dto);
			throw new InvalidEntityException("Le mvt de stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);

		}

		return MvtStkDto.fromEntity(mvtStkRepository.save(MvtStkDto.toEntity(dto)));
	}

	@Override
	public MvtStkDto findById(Integer id) {
		if (id == null) {
			log.error("Mvt Stk id is null");
			return null;
		}

		return mvtStkRepository.findById(id).map(MvtStkDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
				"Aucun MVT_STK avec l'ID " + id + " n'ete trouve dans la BDD ", ErrorCodes.MVT_STK_NOT_FOUND));
	}

	@Override
	public List<MvtStkDto> findAll() {
		return mvtStkRepository.findAll().stream().map(MvtStkDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Mvt stk id is null");
			return;
		}
		mvtStkRepository.deleteById(id);
		
	}

}
