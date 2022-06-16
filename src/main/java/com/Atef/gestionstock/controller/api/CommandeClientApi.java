package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.dto.CommandeClientDto;
import com.Atef.gestionstock.dto.LigneCommandeClientDto;
import com.Atef.gestionstock.model.EtatCommande;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandeclients")
public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT
            + "/commandeclients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Commande Client ", notes = " Cette methode permet d'enregistrer ou modifier un Commande Client ", response = CommandeClientDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'objet Commande Client cree / modifier "),
            @ApiResponse(code = 404, message = "L'objet Commande Client n'est pas valide "),

    })
    CommandeClientDto save(CommandeClientDto dto);

    @PatchMapping (value = APP_ROOT
            + "/commandeclients/update/client/{idCommande}/{idClient}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un client pour Commande Client ", notes = " Cette methode permet de modifier un client pour Commande Client ", response = CommandeClientDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "le client L'objet Commande Client modifier "),
            @ApiResponse(code = 404, message = "L'objet Commande Client n'est pas valide "),

    })
    CommandeClientDto updateClient(@PathVariable("idCommande")Integer idCommande, @PathVariable("idClient")Integer idClient);

    @PatchMapping (value = APP_ROOT
            + "/commandeclients/update/etat/{idCommande}/{etatCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un etat Commande Client ", notes = " Cette methode permet de modifier un etat Commande Client ", response = CommandeClientDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'etat de L'objet Commande Client modifier "),
            @ApiResponse(code = 404, message = "L'objet Commande Client n'est pas valide "),

    })
    CommandeClientDto updateEtatCommande(@PathVariable("idCommande") Integer idCommande,@PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping (value = APP_ROOT
            + "/commandeclients/update/quantite/{idCommande}/{idLigneCommande}/{quantite}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une quantite Commande Client ", notes = " Cette methode permet de modifier une quantite Commande Client ", response = CommandeClientDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "la quantitede de L'objet Commande Client modifier "),
            @ApiResponse(code = 404, message = "L'objet Commande Client n'est pas valide "),

    })
    CommandeClientDto     updateQuatiteCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande,@PathVariable("quantite")  BigDecimal quantite);

    @PatchMapping (value = APP_ROOT
            + "/commandeclients/update/article/{idCommande}/{idLigneCommande}/{idArticle}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un article Commande Client ", notes = " Cette methode permet de modifier un article Commande Client ", response = CommandeClientDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'article de L'objet Commande Client modifier "),
            @ApiResponse(code = 404, message = "L'objet Commande Client n'est pas valide "),

    })
    CommandeClientDto updateArticle(@PathVariable("idCommande")Integer idCommande, @PathVariable("idLigneCommande")Integer idLigneCommande,@PathVariable("idArticle") Integer newIdArticle);


    @DeleteMapping(value = APP_ROOT
            + "/commandeclients/delete/article/{idCommande}/{idLigneCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "suprimer un article Commande Client ", notes = " Cette methode permet de supprimer un article Commande Client ", response = CommandeClientDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'article de L'objet Commande Client supprimer "),
            @ApiResponse(code = 404, message = "L'objet Commande Client n'est pas valide "),

    })
    CommandeClientDto deleteArticle(@PathVariable("idCommande")Integer idCommande , @PathVariable("idLigneCommande")Integer idLigneCommande );

        @GetMapping(value = APP_ROOT + "/commandeclients/{idCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer un Commande Client ", notes = " Cette methode permet de chercher un Commande Client par son ID", response = CommandeClientDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Commande Client a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucun Commande Client existe dans la BDD avec l'ID fournit"),

    })
    CommandeClientDto findById(@PathVariable("idCommandeClient")Integer id);

    @GetMapping(value = APP_ROOT + "/commandeclients/{codeCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer un Commande Client ", notes = " Cette methode permet de chercher un Commande Client par son Code", response = CommandeClientDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Commande Client a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucun Commande Client existe dans la BDD avec le Code fournit"),

    })
    CommandeClientDto findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping(value = APP_ROOT + "/commandeclients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le liste des Commande Client ", notes = " Cette methode permet de chercher et renvoyer la liste des Commande Client qui existe dans la BDD", responseContainer = "List<CommandeClientDto>" )
    @ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des Commande Client / une liste vide  ")

    })
    List<CommandeClientDto> findAll();

    @GetMapping(value = APP_ROOT + "/commandeclients/lignecommande/{idCommande }", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "chercher la liste des Commande Client avec Id commande ", notes = " Cette methode permet de chercher et renvoyer la liste des Commande Client avec ligne commande qui existe dans la BDD", responseContainer = "List<CommandeClientDto>" )
    @ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des Commande Client / une liste vide  ")

    })
    List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(@PathVariable("idCommande") Integer idCommande);

    @DeleteMapping(value = APP_ROOT + "/commandeclients/delete/{idCommandeClient}")
    @ApiOperation(value = "Supprimer un Commande Client ", notes = " Cette methode permet de Supprimer un Commande Client par son ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Commande Client a ete Supprimer "), })
    void delete(@PathVariable("idCommandeClient") Integer id);
}
