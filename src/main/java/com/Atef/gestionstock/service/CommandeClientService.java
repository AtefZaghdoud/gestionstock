package com.Atef.gestionstock.service;

import java.math.BigDecimal;
import java.util.List;

import com.Atef.gestionstock.dto.CommandeClientDto;
import com.Atef.gestionstock.dto.LigneCommandeClientDto;
import com.Atef.gestionstock.model.EtatCommande;

public interface CommandeClientService {

	CommandeClientDto save(CommandeClientDto dto);

	CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

	CommandeClientDto updateQuatiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

	CommandeClientDto updateClient(Integer idCommande, Integer idClient);

	CommandeClientDto updateArticle(Integer idCommande , Integer idLigneCommande, Integer  idArticle);

	CommandeClientDto deleteArticle(Integer idCommande , Integer idLigneCommande );
//	Delete article ==> delete ligne de commande
	CommandeClientDto findById(Integer id);

	CommandeClientDto findByCode(String code);

	List<CommandeClientDto> findAll();

	List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande);
	
	void delete(Integer id);
	

}
