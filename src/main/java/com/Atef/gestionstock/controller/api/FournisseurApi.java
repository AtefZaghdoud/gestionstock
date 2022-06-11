package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.ClientDto;
import com.Atef.gestionstock.dto.FournisseurDto;
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

@Api(APP_ROOT + "/fournisseurs")
public interface FournisseurApi {

    @PostMapping(value = APP_ROOT
            + "/fournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Fournisseur ", notes = " Cette methode permet d'enregistrer ou modifier un Fournisseur ", response = FournisseurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'objet Fournisseur cree / modifier "),
            @ApiResponse(code = 404, message = "L'objet Fournisseur n'est pas valide "),

    })
    FournisseurDto save(FournisseurDto dto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer un Fournisseur ", notes = " Cette methode permet de chercher un Fournisseur par son ID", response = FournisseurDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Fournisseur a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucun Fournisseur existe dans la BDD avec l'ID fournit"),

    })
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "/fournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le liste des Fournisseurs ", notes = " Cette methode permet de chercher et renvoyer la liste des Fournisseurs qui existe dans la BDD", responseContainer = "List<FournisseurDto>" )
    @ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des Fournisseurs / une liste vide  ")

    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{idFournisseur}")
    @ApiOperation(value = "Supprimer un Fournisseur ", notes = " Cette methode permet de Supprimer un Fournisseur par son ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Fournisseur a ete Supprimer "), })
    void delete(@PathVariable("idFournisseur") Integer id);
}
