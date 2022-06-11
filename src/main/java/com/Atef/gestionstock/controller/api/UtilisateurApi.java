package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.ClientDto;
import com.Atef.gestionstock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "/utilisateurs")
public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT
            + "/utilisateurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Utilisateur ", notes = " Cette methode permet d'enregistrer ou modifier un Utilisateur ", response = UtilisateurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'objet Utilisateur cree / modifier "),
            @ApiResponse(code = 404, message = "L'objet Utilisateur n'est pas valide "),

    })
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer un Utilisateur ", notes = " Cette methode permet de chercher un Utilisateur par son ID", response = UtilisateurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'Utilisateur a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucun Utilisateur existe dans la BDD avec l'ID fournit"),

    })
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le liste des Utilisateurs ", notes = " Cette methode permet de chercher et renvoyer la liste des Utilisateurs qui existe dans la BDD", responseContainer = "List<UtilisateurDto>" )
    @ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des Utilisateurs / une liste vide  ")

    })
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}")
    @ApiOperation(value = "Supprimer un Utilisateur ", notes = " Cette methode permet de Supprimer un Utilisateur par son ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'Utilisateur a ete Supprimer "), })
    void delete(@PathVariable("idUtilisateur") Integer id);

}
