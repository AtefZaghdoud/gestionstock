package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.ClientDto;
import com.Atef.gestionstock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/entreprises")
public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT
            + "/entreprises/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Entreprise ", notes = " Cette methode permet d'enregistrer ou modifier une Entreprise ", response = EntrepriseDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'objet Entreprise cree / modifier "),
            @ApiResponse(code = 404, message = "L'objet Entreprise n'est pas valide "),

    })
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(value = APP_ROOT + "/entreprises/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer une Entreprise ", notes = " Cette methode permet de chercher une Entreprise par son ID", response = EntrepriseDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'Entreprise a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucune Entreprise existe dans la BDD avec l'ID fournit"),

    })
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value = APP_ROOT + "/entreprises/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le liste des Entreprises ", notes = " Cette methode permet de chercher et renvoyer la liste des Entreprises qui existe dans la BDD", responseContainer = "List<EntrepriseDto>" )
    @ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des Entreprises / une liste vide  ")

    })
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/entreprises/delete/{idEntreprise}")
    @ApiOperation(value = "Supprimer une Entreprise ", notes = " Cette methode permet de Supprimer une Entreprise par son ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'Entreprise a ete Supprimer "), })
    void delete(@PathVariable("idEntreprise") Integer id);

}
