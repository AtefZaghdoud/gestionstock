package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.CommandeClientDto;
import com.Atef.gestionstock.dto.CommandeFournisseurDto;
import com.Atef.gestionstock.model.EtatCommande;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandefournisseurs")
public interface CommandeFournisseurApi {
    @PostMapping(value = APP_ROOT
            + "/commandefournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Commande Fournisseur ", notes = " Cette methode permet d'enregistrer ou modifier un Commande Fournisseur ", response = CommandeFournisseurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'objet Commande Fournisseur cree / modifier "),
            @ApiResponse(code = 404, message = "L'objet Commande Fournisseur n'est pas valide "),

    })
    CommandeFournisseurDto save(CommandeFournisseurDto dto);

    @PatchMapping (value = APP_ROOT
            + "/commandefournisseurs/update/etat/{idCommande}/{etatCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un etat Commande Fournisseur ", notes = " Cette methode permet de modifier un etat Commande Fournisseur ", response = CommandeFournisseurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'etat de L'objet Commande Fournisseur modifier "),
            @ApiResponse(code = 404, message = "L'objet Commande Fournisseur n'est pas valide "),

    })
    CommandeFournisseurDto updateEtatCommande(@PathVariable("idCommande") Integer idCommande,@PathVariable("etatCommande")  EtatCommande etatCommande);

    @PatchMapping (value = APP_ROOT
            + "/commandefournisseurs/update/quantite/{idCommande}/{idLigneCommande}/{quantite}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une quantite Commande Fournisseur ", notes = " Cette methode permet de modifier une quantite Commande Fournisseur ", response = CommandeFournisseurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "la quantitede de L'objet Commande Fournisseur modifier "),
            @ApiResponse(code = 404, message = "L'objet Commande Fournisseur n'est pas valide "),

    })
    CommandeFournisseurDto updateQuatiteCommande(@PathVariable("idCommande") Integer idCommande,@PathVariable("idLigneCommande")  Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(value = APP_ROOT
            + "/commandefournisseurs/update/client/{idCommande}/{idFournisseur}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un client pour Commande Fournisseur ", notes = " Cette methode permet de modifier un Fournisseur pour Commande Fournisseur ", response = CommandeFournisseurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "le client L'objet Commande Fournisseur modifier "),
            @ApiResponse(code = 404, message = "L'objet Commande Fournisseur n'est pas valide "),

    })
    CommandeFournisseurDto updateFournisseur(@PathVariable("idCommande") Integer idCommande, @PathVariable("idFournisseur") Integer idFournisseur);

    @PatchMapping (value = APP_ROOT
            + "/commandefournisseurs/update/article/{idCommande}/{idLigneCommande}/{idArticle}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un article Commande Fournisseur ", notes = " Cette methode permet de modifier un article Commande Fournisseur ", response = CommandeFournisseurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'article de L'objet Commande Fournisseur modifier "),
            @ApiResponse(code = 404, message = "L'objet Commande Fournisseur n'est pas valide "),

    })
    CommandeFournisseurDto updateArticle(@PathVariable("idCommande") Integer idCommande ,@PathVariable("idLigneCommande")  Integer idLigneCommande,@PathVariable("idArticle")  Integer  idArticle);

    @DeleteMapping(value = APP_ROOT
            + "/commandefournisseurs/delete/article/{idCommande}/{idLigneCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "suprimer un article Commande Fournisseur ", notes = " Cette methode permet de supprimer un article Commande Fournisseur ", response = CommandeFournisseurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "l'article de L'objet Commande Fournisseur supprimer "),
            @ApiResponse(code = 404, message = "L'objet Commande Fournisseur n'est pas valide "),

    })
    CommandeFournisseurDto deleteArticle(@PathVariable("idCommande") Integer idCommande , @PathVariable("idLigneCommande") Integer idLigneCommande );

    @GetMapping(value = APP_ROOT + "/commandefournisseurs/{idCommandeFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer un Commande Fournisseur ", notes = " Cette methode permet de chercher un Commande Fournisseur par son ID", response = CommandeFournisseurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Commande Fournisseur a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucun Commande Fournisseur existe dans la BDD avec l'ID fournit"),

    })
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur")Integer id);

    @GetMapping(value = APP_ROOT + "/commandefournisseurs/{codeCommandeFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer un Commande Fournisseur ", notes = " Cette methode permet de chercher un Commande Fournisseur par son Code", response = CommandeFournisseurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Commande Fournisseur a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucun Commande Fournisseur existe dans la BDD avec le Code fournit"),

    })
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(value = APP_ROOT + "/commandefournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le liste des Commande Fournisseur ", notes = " Cette methode permet de chercher et renvoyer la liste des Commande Client qui existe dans la BDD", responseContainer = "List<CommandeFournisseurDto>" )
    @ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des Commande Client / une liste vide  ")

    })
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandefournisseurs/delete/{idCommandeFournisseur}")
    @ApiOperation(value = "Supprimer un Commande Fournisseur ", notes = " Cette methode permet de Supprimer un Commande Fournisseur par son ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Commande Fournisseur a ete Supprimer "), })
    void delete(@PathVariable("idCommandeFournisseur") Integer id);
}
