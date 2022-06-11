package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.CommandeClientDto;
import com.Atef.gestionstock.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
