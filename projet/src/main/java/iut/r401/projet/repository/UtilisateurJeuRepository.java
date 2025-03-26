package iut.r401.projet.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import iut.r401.projet.model.Jeu;
import iut.r401.projet.model.Utilisateur;
import iut.r401.projet.model.UtilisateurJeu;

public interface UtilisateurJeuRepository extends JpaRepository<UtilisateurJeu, Long> {
    List<UtilisateurJeu> findByUtilisateur(Utilisateur utilisateur);
    Optional<UtilisateurJeu> findByUtilisateurAndJeu(Utilisateur utilisateur,Jeu jeu);
}

