package com.Atef.gestionstock.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Atef.gestionstock.dto.FournisseurDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.repository.FournisseurRepository;
import com.Atef.gestionstock.service.FournisseurService;
import com.Atef.gestionstock.validator.FournisseurValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService{

	
	private FournisseurRepository fournisseurRepository;

	@Autowired
	public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
		this.fournisseurRepository= fournisseurRepository;
	}
	
	
	@Override
	public FournisseurDto save(FournisseurDto dto) {
		List<String> errors = FournisseurValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Fournisseur not Valid {}", dto);
			throw new InvalidEntityException("Le Fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);

		}

		return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(dto)));
	}

	@Override
	public FournisseurDto findById(Integer id) {
		if (id == null) {
			log.error("Fournisseur id is null");
			return null;
		}

		return fournisseurRepository.findById(id).map(FournisseurDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
				"Aucun Fournisseur avec l'ID " + id + " n'ete trouve dans la BDD ", ErrorCodes.FOURNISSEUR_NOT_FOUND));
	}

	@Override
	public List<FournisseurDto> findAll() {
		return fournisseurRepository.findAll().stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Fournisseur id is null");
			return;
		}
		fournisseurRepository.deleteById(id);
		
	}

}
