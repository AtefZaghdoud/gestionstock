package com.Atef.gestionstock.controller.api;

import com.Atef.gestionstock.dto.ArticleDto;
import com.Atef.gestionstock.dto.auth.AuthenticationRequest;
import com.Atef.gestionstock.dto.auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/auth/")
public interface AuthenticationApi {

    @PostMapping(value = APP_ROOT
            + "/auth/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "S'authentifier ", notes = " Cette methode permet d'authentifier  ", response = AuthenticationResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = " Sucess "),
            @ApiResponse(code = 404, message = " Email et/ou Password Incorrect "),

    })
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request);
}
