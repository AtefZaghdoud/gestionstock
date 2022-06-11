package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/clients")
public interface ClientApi {


    @PostMapping(value = APP_ROOT
            + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Client ", notes = " Cette methode permet d'enregistrer ou modifier un Client ", response = ClientDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'objet Client cree / modifier "),
            @ApiResponse(code = 404, message = "L'objet Client n'est pas valide "),

    })
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer un Client ", notes = " Cette methode permet de chercher un Client par son ID", response = ClientDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Client a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucun Client existe dans la BDD avec l'ID fournit"),

    })
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le liste des Clients ", notes = " Cette methode permet de chercher et renvoyer la liste des Clients qui existe dans la BDD", responseContainer = "List<ClientDto>" )
    @ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des Clients / une liste vide  ")

    })
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    @ApiOperation(value = "Supprimer un Client ", notes = " Cette methode permet de Supprimer un Client par son ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Client a ete Supprimer "), })
    void delete(@PathVariable("idClient") Integer id);
}
