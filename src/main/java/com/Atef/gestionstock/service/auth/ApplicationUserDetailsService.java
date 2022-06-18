package com.Atef.gestionstock.service.auth;

import com.Atef.gestionstock.dto.UtilisateurDto;
import com.Atef.gestionstock.exception.EntityNotFoundException;
import com.Atef.gestionstock.exception.ErrorCodes;
import com.Atef.gestionstock.model.Utilisateur;
import com.Atef.gestionstock.model.auth.ExtendedUser;
import com.Atef.gestionstock.repository.UtilisateurRepository;
import com.Atef.gestionstock.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    /*@Autowired
    private UtilisateurRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = repository.findUtilisateurByEmail(email).orElseThrow(() ->
                new EntityNotFoundException(" Aucun utilisateur avec l'email fournit ", ErrorCodes.UTILISATEUR_NOT_FOUND)
            ) ;

        return new User(utilisateur.getEmail(), utilisateur.getMotDePasse(), Collections.emptyList()) {
        };
    }*/

//    @Autowired
//    private UtilisateurRepository repository;

    @Autowired
   private UtilisateurService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       UtilisateurDto utilisateur = service.findUtilisateurByEmail(email);
//        Utilisateur utilisateur1 = repository.findUtilisateurByEmail(email).orElseThrow(() ->
//                new EntityNotFoundException(" Aucun utilisateur avec l'email fournit ", ErrorCodes.UTILISATEUR_NOT_FOUND)
//        ) ;
//        UtilisateurDto utilisateur = UtilisateurDto.fromEntity(utilisateur1);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(!(utilisateur.getRoles()==null))
        {
            utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        }
        return new ExtendedUser(utilisateur.getEmail(),utilisateur.getMotDePasse(),utilisateur.getEntreprise().getId() , authorities) ;

    }

}
