package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @DeleteMapping(value = APP_ROOT + "/commandeclients/delete/{idCommandeClient}")
    @ApiOperation(value = "Supprimer un Commande Client ", notes = " Cette methode permet de Supprimer un Commande Client par son ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Commande Client a ete Supprimer "), })
    void delete(@PathVariable("idCommandeClient") Integer id);
}
