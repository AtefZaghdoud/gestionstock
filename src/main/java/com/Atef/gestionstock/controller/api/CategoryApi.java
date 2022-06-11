package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT
            + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie ", notes = " Cette methode permet d'enregistrer ou modifier une categorie ", response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L'objet categorie cree / modifier "),
            @ApiResponse(code = 404, message = "L'objet categorie n'est pas valide "),

    })
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer une categorie ", notes = " Cette methode permet de chercher une categorie par son ID", response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Le categorie a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucune categorie existe dans la BDD avec l'ID fournit"),

    })
    CategoryDto findById(@PathVariable("idCategorie") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{codeCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "cherhcer une categorie", notes = " Cette methode permet de chercher une categorie par son Code", response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Le categorie a ete trouver dans la BDD "),
            @ApiResponse(code = 404, message = "Aucune categorie existe dans la BDD avec le Code fournit"),

    })
    CategoryDto findByCodeCategory(@PathVariable("codeCategorie") String CodeCategory);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le liste des categories ", notes = " Cette methode permet de chercher et renvoyer la liste des categories qui existe dans la BDD", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La liste des categories / une liste vide  ")

    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategorie}")
    @ApiOperation(value = "Supprimer une categorie ", notes = " Cette methode permet de Supprimer une categorie par son ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Le categorie a ete Supprimer "),})
    void delete(@PathVariable("idCategorie") Integer id);
}
