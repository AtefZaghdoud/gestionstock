package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.ClientDto;
import com.flickr4java.flickr.FlickrException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;
import java.io.InputStream;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;
/*
@Api(APP_ROOT + "/flickr")
public interface FlickrApi {
    @PostMapping(value = APP_ROOT
            + "/flickr/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation(value = "Enregistrer une photo ", notes = " Cette methode permet d'enregistrer ou modifier une photo ", response = )
    @ApiResponses(value = { @ApiResponse(code = 200, message = " une photo cree / modifier "),
            @ApiResponse(code = 404, message = "une photo n'est pas valide "),

    })
    String savePhoto(InputStream photo , String title ) throws FlickrException;
}
*/