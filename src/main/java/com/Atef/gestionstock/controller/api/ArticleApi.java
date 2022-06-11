package com.Atef.gestionstock.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Atef.gestionstock.dto.ArticleDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/articles")
public interface ArticleApi {

	@PostMapping(value = APP_ROOT
			+ "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un article ", notes = " Cette methode permet d'enregistrer ou modifier un article ", response = ArticleDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "L'objet article cree / modifier "),
			@ApiResponse(code = 404, message = "L'objet article n'est pas valide "),

	})
	ArticleDto save(@RequestBody ArticleDto dto);

	@GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "cherhcer un article ", notes = " Cette methode permet de chercher un article par son ID", response = ArticleDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "L'article a ete trouver dans la BDD "),
			@ApiResponse(code = 404, message = "Aucun article existe dans la BDD avec l'ID fournit"),

	})
	ArticleDto findById(@PathVariable("idArticle") Integer id);

	@GetMapping(value = APP_ROOT + "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "cherhcer un article ", notes = " Cette methode permet de chercher un article par son Code", response = ArticleDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "L'article a ete trouver dans la BDD "),
			@ApiResponse(code = 404, message = "Aucun article existe dans la BDD avec le Code fournit"),

	})
	ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

	@GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Renvoi le liste des articles ", notes = " Cette methode permet de chercher et renvoyer la liste des articles qui existe dans la BDD", responseContainer = "List<ArticleDto>" )
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des article / une liste vide  ")

	})
	List<ArticleDto> findAll();

	@DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")
	@ApiOperation(value = "Supprimer un article ", notes = " Cette methode permet de Supprimer un article par son ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "L'article a ete Supprimer "), })
	void delete(@PathVariable("idArticle") Integer id);

}
