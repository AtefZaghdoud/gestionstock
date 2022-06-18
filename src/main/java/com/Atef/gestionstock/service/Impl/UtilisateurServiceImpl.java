package com.Atef.gestionstock.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.dto.ChangerMotDePasseUtilisateurDto;
import com.Atef.gestionstock.exception.InvalidOperationException;
import com.Atef.gestionstock.model.Article;
import com.Atef.gestionstock.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Atef.gestionstock.dto.UtilisateurDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.repository.UtilisateurRepository;
import com.Atef.gestionstock.service.UtilisateurService;
import com.Atef.gestionstock.validator.UtilisateurValidator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurRepository utilisateurRepository;

	@Autowired
	public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository= utilisateurRepository;
	}

	@Override
	public UtilisateurDto save(UtilisateurDto dto) {
		List<String> errors = UtilisateurValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Utilisateur is not Valid {}", dto);
			throw new InvalidEntityException("L'Utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);

		}

		return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(dto)));
	}

	@Override
	public UtilisateurDto findById(Integer id) {
		if (id == null) {
			log.error("Utilisateur id is null");
			return null;
		}

		return utilisateurRepository.findById(id).map(UtilisateurDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
				"Aucun utilisateur avec l'ID " + id + " n'ete trouve dans la BDD ", ErrorCodes.UTILISATEUR_NOT_FOUND));
	}
//
//	@Override
//	public UtilisateurDto findUtilisateurByEmail(String email){
//		if (email==null){
//			log.error("Utilisateur email is null ");
//			return null;
//		}
//
//		Optional<Utilisateur> utilisateur = utilisateurRepository.findUtilisateurByEmail(email);
//
//		UtilisateurDto dto = UtilisateurDto.fromEntity(utilisateur.get());
//
//		return Optional.of(dto).orElseThrow( () ->
//				new EntityNotFoundException("Aucun utilisateur avec l'email'  " + email + "n'ete trouver dans la base de données " ,
//						ErrorCodes.UTILISATEUR_NOT_FOUND)  ) ;
//	}
//
	@Override
	public List<UtilisateurDto> findAll() {
		return utilisateurRepository.findAll().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Client id is null");
			return;
		}
		utilisateurRepository.deleteById(id);

	}

	public UtilisateurDto findUtilisateurByEmail(String email){
		return utilisateurRepository.findUtilisateurByEmail(email)
				.map(UtilisateurDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun utilisateur avec l'email = " + email + "n'ete trouve dans la BDD ",
						ErrorCodes.UTILISATEUR_NOT_FOUND
				));
	}

	@Override
	public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto) {
		validate(dto);
		Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(dto.getId());
		if (utilisateurOptional.isEmpty()) {
			log.warn("Aucun utilisateur n'a ete trouve avec cette Id" + dto.getId());
			throw new EntityNotFoundException("Aucun utilisateur n'a ete trouve avec cette Id", ErrorCodes.UTILISATEUR_NOT_FOUND);
		}
		Utilisateur utilisateur = utilisateurOptional.get();
		utilisateur.setMotDePasse(dto.getMotDePasse());
		return UtilisateurDto.fromEntity(
				utilisateurRepository.save(utilisateur)
		);
	}

	private void validate(ChangerMotDePasseUtilisateurDto dto){
		if (dto == null) {
			log.warn("impossible de modifier le mot de passe avec un objet null");
			throw new InvalidOperationException("Aucun information n'a ete fournit pour avoir changer le mot de passe ",ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		}
		if (dto.getId() == null) {
			log.warn("impossible de modifier le mot de passe avec un ID null");
			throw new InvalidOperationException("ID utilisateur null : impossible de modifier le mot de passe  ",ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		}
		if (!StringUtils.hasLength(dto.getMotDePasse()) || !StringUtils.hasLength(dto.getConfirmeMotDePasse()) ) {
			log.warn("impossible de modifier le mot de passe avec un mot de passe null");
			throw new InvalidOperationException("Mot de Passe utilisateur null : impossible de modifier le mot de passe  ",ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		}
		if ( !dto.getMotDePasse().equals(dto.getConfirmeMotDePasse())) {
			log.warn("impossible de modifier le mot de passe avec deux mot de passe differents");
			throw new InvalidOperationException("Mot de Passe utilisateur non conforme : impossible de modifier le mot de passe  ",ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		}
	}
}
