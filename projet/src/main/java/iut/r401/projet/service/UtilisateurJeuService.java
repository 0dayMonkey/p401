package iut.r401.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iut.r401.projet.model.Jeu;
import iut.r401.projet.model.Utilisateur;
import iut.r401.projet.model.UtilisateurJeu;
import iut.r401.projet.repository.JeuRepository;
import iut.r401.projet.repository.UtilisateurJeuRepository;
import iut.r401.projet.repository.UtilisateurRepository;

@Service
public class UtilisateurJeuService {
    @Autowired
    private UtilisateurJeuRepository utilisateurJeuRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private JeuRepository jeuRepository;

    public UtilisateurJeu ajouterJeuABibliotheque(Long idUtilisateur,Long idJeu,String statut,int note,String commentaire){
        //CHECK EXIST
        Optional<Utilisateur> optUtilisateur=utilisateurRepository.findById(idUtilisateur);
        if(optUtilisateur.isEmpty()){
            return null;
        }
        Utilisateur utilisateur=optUtilisateur.get();
        Optional<Jeu> optJeu=jeuRepository.findById(idJeu);
        if(optJeu.isEmpty()) {
            return null;
        }
        Jeu jeu=optJeu.get();


        Optional<UtilisateurJeu> dejaPresent=utilisateurJeuRepository.findByUtilisateurAndJeu(utilisateur,jeu);
        if(dejaPresent.isPresent()){
            return null;
        }

        // RELATION CREATE ( PAS OPTI MAIS PRIMAIRE )
        UtilisateurJeu utilisateurJeu = new UtilisateurJeu(statut,note,commentaire,utilisateur,jeu);
        return utilisateurJeuRepository.save(utilisateurJeu);
    }

    public List<UtilisateurJeu> recupererBibliotheque(Long idUtilisateur){
        Optional<Utilisateur> optUtilisateur=utilisateurRepository.findById(idUtilisateur);
        if (optUtilisateur.isEmpty()){
            return List.of();
        }
        return utilisateurJeuRepository.findByUtilisateur(optUtilisateur.get());
    }

    public UtilisateurJeu modifierUtilisateurJeu(Long idUtilisateurJeu,String nouveauStatut,int nouvelleNote,String nouveauCommentaire){
        Optional<UtilisateurJeu> optUtilisateurJeu=utilisateurJeuRepository.findById(idUtilisateurJeu);
        if (optUtilisateurJeu.isPresent()){
            UtilisateurJeu utilisateurJeu=optUtilisateurJeu.get();
            utilisateurJeu.setStatut(nouveauStatut);
            utilisateurJeu.setNote(nouvelleNote);
            utilisateurJeu.setCommentaire(nouveauCommentaire);
            return utilisateurJeuRepository.save(utilisateurJeu);
        } else{
            return null;
        }
    }

    public void supprimerDeBibliotheque(Long idUtilisateurJeu){
        utilisateurJeuRepository.deleteById(idUtilisateurJeu);
    }
}
