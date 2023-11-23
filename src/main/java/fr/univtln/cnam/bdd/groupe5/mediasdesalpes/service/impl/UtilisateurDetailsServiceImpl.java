package fr.univtln.cnam.bdd.groupe5.mediasdesalpes.service.impl;

import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.entity.UtilisateurEntity;
import fr.univtln.cnam.bdd.groupe5.mediasdesalpes.repository.IUtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsServiceImpl implements UserDetailsService {

    private IUtilisateurRepository utilisateurRepository;

    public UtilisateurDetailsServiceImpl(IUtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurEntity user = utilisateurRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmailutilisateur())
                .password(user.getMdputilisateur())
                .roles(user.getTypeprofil().equals(UtilisateurEntity.TypeProfil.ADMINISTRATEUR) ? "ADMIN" : "USER")
                .build();
    }
}

