package iut.r401.projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iut.r401.projet.model.Utilisateur;
import iut.r401.projet.repository.UtilisateurRepository;

@Service
public class AuthService {

    @Autowired
    private UtilisateurRepository utilisateurRepo;

    public Utilisateur updateUtilisateur(Long id,Utilisateur utilisateur) {
        Utilisateur existingUtilisateur=utilisateurRepo.findById(id)
        .orElseThrow(()->new RuntimeException("user introuvable")); // on peut retirer 
        existingUtilisateur.setPseudo(utilisateur.getPseudo());
        existingUtilisateur.setEmail(utilisateur.getEmail());
        return utilisateurRepo.save(existingUtilisateur);
    }
}
