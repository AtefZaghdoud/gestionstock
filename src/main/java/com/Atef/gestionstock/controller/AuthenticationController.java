package com.Atef.gestionstock.controller;

import com.Atef.gestionstock.dto.auth.AuthenticationRequest;
import com.Atef.gestionstock.dto.auth.AuthenticationResponse;
import com.Atef.gestionstock.model.auth.ExtendedUser;
import com.Atef.gestionstock.service.auth.ApplicationUserDetailsService;
import com.Atef.gestionstock.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.Atef.gestionstock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){

           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           request.getLogin(),
                           request.getPassword()
                   )
           );

      final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);

         return  ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }

}
