package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.ClientDto;
import com.Atef.gestionstock.dto.MvtStkDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/mvtstk")
public interface MvtStkApi {


    @GetMapping(APP_ROOT + "/mvtstk/stockreel/{idArticle}")
    BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(APP_ROOT + "/mvtstk/filter/article/{idArtilce}")
    List<MvtStkDto> mvtStkArticle(@PathVariable("idArticle")Integer idArticle);

    @PostMapping(APP_ROOT + "/mvtstk/entree")
    MvtStkDto entreeStock(@RequestBody MvtStkDto dto);

    @PostMapping(APP_ROOT + "/mvtstk/sortie")
    MvtStkDto sortieStock(@RequestBody MvtStkDto dto);

    @PostMapping(APP_ROOT + "/mvtstk/correctionpos")
    MvtStkDto correctionStockPos(@RequestBody MvtStkDto dto);

    @PostMapping(APP_ROOT + "/mvtstk/correctionneg")
    MvtStkDto correctionStockNeg(@RequestBody MvtStkDto dto);


//    @PostMapping(value = APP_ROOT
//            + "/mvtstk/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Enregistrer un MvtStk ", notes = " Cette methode permet d'enregistrer ou modifier un MvtStk ", response = MvtStkDto.class)
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "L'objet MvtStk cree / modifier "),
//            @ApiResponse(code = 404, message = "L'objet MvtStk n'est pas valide "),
//
//    })
//    MvtStkDto save(MvtStkDto dto);
//
//    @GetMapping(value = APP_ROOT + "/mvtstk/{idMvtStk}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "cherhcer un MvtStk ", notes = " Cette methode permet de chercher un MvtStk par son ID", response = MvtStkDto.class)
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le MvtStk a ete trouver dans la BDD "),
//            @ApiResponse(code = 404, message = "Aucun MvtStk existe dans la BDD avec l'ID fournit"),
//
//    })
//    MvtStkDto findById(@PathVariable("idMvtStk") Integer id);
//
//    @GetMapping(value = APP_ROOT + "/mvtstk/all", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Renvoi le liste des MvtStk ", notes = " Cette methode permet de chercher et renvoyer la liste des MvtStk qui existe dans la BDD", responseContainer = "List<MvtStkDto>" )
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des MvtStk / une liste vide  ")
//
//    })
//    List<MvtStkDto> findAll();
//
//    @DeleteMapping(value = APP_ROOT + "/mvtstk/delete/{idMvtStk}")
//    @ApiOperation(value = "Supprimer un MvtStk ", notes = " Cette methode permet de Supprimer un MvtStk par son ID")
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "Le MvtStk a ete Supprimer "), })
//    void delete(@PathVariable("idMvtStk") Integer id);
}
