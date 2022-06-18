package com.Atef.gestionstock.config;

import com.Atef.gestionstock.service.auth.ApplicationUserDetailsService;
import com.Atef.gestionstock.utils.JwtUtil;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApplicationRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null ;
        String idEtreprise = null ;
        if (authHeader != null && authHeader.startsWith("Bearer")){
            jwt = authHeader.substring(7) ;
            username = jwtUtil.extractUsername(jwt);
            idEtreprise = jwtUtil.extractidEtreprise(jwt);
        }

        if ( username != null && SecurityContextHolder.getContext().getAuthentication() == null ){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if ( jwtUtil.validateToken(jwt,userDetails));{
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails , null,userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }
        MDC.put("idEntreprise",idEtreprise);
        filterChain.doFilter(request,response);



    }
}
