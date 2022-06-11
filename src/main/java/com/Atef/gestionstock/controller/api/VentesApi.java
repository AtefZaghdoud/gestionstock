package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.dto.VenteDto;
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

@Api(APP_ROOT + "/ventes")
public interface VentesApi {

    @PostMapping(value = APP_ROOT
            + "/ventes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Vente ", notes = " Cette methode permet d'enregistrer ou modifier une Vente ", response = VenteDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'objet Vente cree / modifier "),
            @ApiResponse(code = 404, message = "L'objet Vente n'est pas valide "),

    })
    VenteDto save(VenteDto dto);

    @GetMapping(value = APP_ROOT + "/ventes/{idVente}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer un article ", notes = " Cette methode permet de chercher une Vente par son ID", response = VenteDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Vente a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucune Vente existe dans la BDD avec l'ID fournit"),

    })
    VenteDto findById(@PathVariable("idVente") Integer id);

    @GetMapping(value = APP_ROOT + "/ventes/{codeVente}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer une Vente ", notes = " Cette methode permet de chercher une Vente par son Code", response = VenteDto.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Vente a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucune Vente existe dans la BDD avec le Code fournit"),

    })
    VenteDto findByCode(@PathVariable("codeVente") String Code);

    @GetMapping(value = APP_ROOT + "/ventes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le liste des Ventes ", notes = " Cette methode permet de chercher et renvoyer la liste des Ventes qui existe dans la BDD", responseContainer = "List<VenteDto>" )
    @ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des Ventes / une liste vide  ")

    })
    List<VenteDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/ventes/delete/{idVente}")
    @ApiOperation(value = "Supprimer une Vente ", notes = " Cette methode permet de Supprimer une Vente par son ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le Vente a ete Supprimer "), })
    void delete(@PathVariable("idVente") Integer id);
}
