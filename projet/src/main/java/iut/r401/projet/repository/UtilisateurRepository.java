package iut.r401.projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import iut.r401.projet.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByPseudo(String pseudo);
    Optional<Utilisateur> findByEmail(String email);
}
