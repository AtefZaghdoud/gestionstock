package com.Atef.gestionstock.service.Impl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.Atef.gestionstock.dto.RoleDto;
import com.Atef.gestionstock.dto.UtilisateurDto;
import com.Atef.gestionstock.repository.RoleRepository;
import com.Atef.gestionstock.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Atef.gestionstock.dto.EntrepriseDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.repository.EntrepriseRepository;
import com.Atef.gestionstock.service.EntrepriseService;
import com.Atef.gestionstock.validator.EntrepriseValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService{

	private EntrepriseRepository entrepriseRepository;
	private UtilisateurService utilisateurService;
	private RoleRepository rolesRepository ;

	@Autowired
	public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
		this.entrepriseRepository= entrepriseRepository;
	}
	
	@Override
	public EntrepriseDto save(EntrepriseDto dto) {
		List<String> errors = EntrepriseValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Entreprise not Valid {}", dto);
			throw new InvalidEntityException("L'Entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);

		}
		EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
				entrepriseRepository.save(EntrepriseDto.toEntity(dto)));

		UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

		UtilisateurDto savedUser = utilisateurService.save(utilisateur);

		RoleDto roleDto = RoleDto.builder()
				.roleName("ADMIN")
				.utilisateur(savedUser)
				.build();
		rolesRepository.save(RoleDto.toEntity(roleDto));
		return savedEntreprise;

	}

	private UtilisateurDto fromEntreprise(EntrepriseDto dto){
		return  UtilisateurDto.builder()
				.adresse(dto.getAdresse())
				.nom(dto.getNom())
				.prenom(dto.getCodeFiscal())
				.email(dto.getEmail())
				.motDePasse("atef")
				.dateDeNaissance(Instant.now())
				.entreprise(dto)
				.photo(dto.getPhoto())
				.build();
	}

	@Override
	public EntrepriseDto findById(Integer id) {
		if (id == null) {
			log.error("Entreprise id is null");
			return null;
		}

		return entrepriseRepository.findById(id).map(EntrepriseDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
				"Aucun Entreprise avec l'ID " + id + " n'ete trouve dans la BDD ", ErrorCodes.ENTREPRISE_NOT_FOUND));
	}

	@Override
	public List<EntrepriseDto> findAll() {
		return entrepriseRepository.findAll().stream().map(EntrepriseDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Entreprise id is null");
			return;
		}
		entrepriseRepository.deleteById(id);
		
	}

}
