package com.Atef.gestionstock.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.Atef.gestionstock.dto.CommandeClientDto;
import com.Atef.gestionstock.dto.LigneCommandeClientDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.exception.InvalidEntityException;
import com.Atef.gestionstock.model.Article;
import com.Atef.gestionstock.model.Client;
import com.Atef.gestionstock.model.CommandeClient;
import com.Atef.gestionstock.model.LigneCommandeClient;
import com.Atef.gestionstock.repository.ArticleRepository;
import com.Atef.gestionstock.repository.ClientRepository;
import com.Atef.gestionstock.repository.CommandeClientRepository;
import com.Atef.gestionstock.repository.LigneCommandeClientRepository;
import com.Atef.gestionstock.service.CommandeClientService;
import com.Atef.gestionstock.validator.CommandeClientValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {
	
	private CommandeClientRepository commandeClientRepository;
	private LigneCommandeClientRepository ligneCommandeClientRepository;
	private ClientRepository clientRepository;
	private ArticleRepository articleRepository;
	
	
	
	@Autowired
	public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
			ClientRepository clientRepository, ArticleRepository articleRepository ,LigneCommandeClientRepository ligneCommandeClientRepository) {
		super();
		this.commandeClientRepository = commandeClientRepository;
		this.clientRepository = clientRepository;
		this.articleRepository = articleRepository;
		this.ligneCommandeClientRepository=ligneCommandeClientRepository;
	}

	@Override
	public CommandeClientDto save(CommandeClientDto dto) {

		List<String> errors = CommandeClientValidator.validate(dto);
		
		if (!errors.isEmpty()) {
			log.error("Commande client n'est pas valide ");
			throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
		}
		
		Optional<Client> client = clientRepository.findById(dto.getClient().getId()) ;
		if (client.isEmpty()) {
			log.error("Client with ID {} was not found in the DB",dto.getClient().getId());
			throw new EntityNotFoundException("Aucun client avec l'ID"+dto.getClient().getId()+"n'a ete trouve dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
		}
		
		List<String > articleErrors = new ArrayList<>();
		
		if (dto.getLigneCommandeClients() != null) {
			dto.getLigneCommandeClients().forEach( ligneCommandeClient -> {
				if (ligneCommandeClient.getArticle()!=null ) {
				
					Optional<Article> article = articleRepository.findById(ligneCommandeClient.getArticle().getId());
					if (article.isEmpty()) {
						articleErrors.add("L'article avec l'ID "+ ligneCommandeClient.getArticle().getId() + "n'existe pas ");
					}
				} else {
					articleErrors.add(" Impossible d'enregistrer une commande avec un article NULL ");
				}
				
			});
			
		}
		
		if (!articleErrors.isEmpty()) {
			log.error("");
			throw new InvalidEntityException("Article n'existe pas dans la BDD ", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
		}
		
		CommandeClient savedCommandeClient	= commandeClientRepository.save(CommandeClientDto.toEntity(dto));
		
		if (dto.getLigneCommandeClients() != null) {
			dto.getLigneCommandeClients().forEach(ligCommandeClient -> {
				LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCommandeClient);
				ligneCommandeClient.setCommandeClient(savedCommandeClient);
				ligneCommandeClientRepository.save(ligneCommandeClient);
			});	
		}
		
		
		return CommandeClientDto.fromEntity(savedCommandeClient);
	}

	@Override
	public CommandeClientDto findById(Integer id) {
		if (id == null) {
			log.error("commande client ID is null");
			return null;
		}
		
		return commandeClientRepository.findById(id)
				.map(CommandeClientDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune Commande client ,'a ete trouve avec l'ID "+ id , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
	}

	@Override
	public CommandeClientDto findByCode(String code) {
		if (!StringUtils.hasLength(code)) {
			log.error("commande client Code is null");
			return null;
		}
		return commandeClientRepository.findCommandeClientByCode(code)
				.map(CommandeClientDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune Commande client ,'a ete trouve avec l'Code "+ code , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
	}

	@Override
	public List<CommandeClientDto> findAll() {
		
		return commandeClientRepository.findAll().stream()
				.map(CommandeClientDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("commande client ID is null");
			return ;
		}
		commandeClientRepository.deleteById(id);
		
	}
	

}
