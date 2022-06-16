package com.Atef.gestionstock.controller;

import com.Atef.gestionstock.controller.api.CommandeClientApi;
import com.Atef.gestionstock.dto.CommandeClientDto;
import com.Atef.gestionstock.dto.LigneCommandeClientDto;
import com.Atef.gestionstock.model.EtatCommande;
import com.Atef.gestionstock.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService ;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return commandeClientService.updateEtatCommande(idCommande,etatCommande);
    }

    @Override
    public CommandeClientDto updateQuatiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return commandeClientService.updateQuatiteCommande(idCommande,idLigneCommande,quantite);
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
        return commandeClientService.updateClient(idClient,idCommande);
    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle) {
        return commandeClientService.updateArticle(idCommande,idLigneCommande,newIdArticle);
    }

    @Override
    public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return commandeClientService.deleteArticle(idCommande,idLigneCommande);
    }

    @Override
    public List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
        return commandeClientService.findAllLignesCommandesClientByCommandeClientId(idCommande);
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        return commandeClientService.save(dto);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        return commandeClientService.findById(id);
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        return commandeClientService.findByCode(code);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeClientService.delete(id);
    }
}
