package iut.r401.projet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iut.r401.projet.model.Utilisateur;
import iut.r401.projet.repository.UtilisateurRepository;

@Service
public class UserService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> trouverParId(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur modifierProfil(Long idUtilisateur,String nouveauPseudo,String nouvelEmail,String nouvelleUrlAvatar) {
    Optional<Utilisateur> optUtilisateur=utilisateurRepository.findById(idUtilisateur);
    if(optUtilisateur.isPresent()){
        Utilisateur utilisateur=optUtilisateur.get();
        utilisateur.setPseudo(nouveauPseudo);
        utilisateur.setEmail(nouvelEmail);
        return utilisateurRepository.save(utilisateur);
    }else{
        return null;
    }
}
}


