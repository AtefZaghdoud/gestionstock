package com.Atef.gestionstock.service;

import java.math.BigDecimal;
import java.util.List;

import com.Atef.gestionstock.dto.CommandeClientDto;
import com.Atef.gestionstock.dto.CommandeFournisseurDto;
import com.Atef.gestionstock.dto.CommandeFournisseurDto;
import com.Atef.gestionstock.model.EtatCommande;

public interface CommandeFournisseurService {

	CommandeFournisseurDto save(CommandeFournisseurDto dto);

	CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

	CommandeFournisseurDto updateQuatiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

	CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);

	CommandeFournisseurDto updateArticle(Integer idCommande , Integer idLigneCommande, Integer  idArticle);

	CommandeFournisseurDto deleteArticle(Integer idCommande , Integer idLigneCommande );
//	Delete article ==> delete ligne de commande

	CommandeFournisseurDto findById(Integer id);
	
	CommandeFournisseurDto findByCode(String code);

	List<CommandeFournisseurDto> findAll();
	
	void delete(Integer id);
}
