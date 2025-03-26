package iut.r401.projet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import iut.r401.projet.model.Jeu;

public interface JeuRepository extends JpaRepository<Jeu, Long> {
    List<Jeu> findByTitreLike(String titre);
    List<Jeu> findByGenreLike(String genre);
    List<Jeu> findByPlateformeLike(String plateforme);
    List<Jeu> findByTitreLikeAndGenreLikeAndPlateformeLike(String titre, String genre, String plateforme);
    
    // Combinaisons pour la recherche
    List<Jeu> findByTitreLikeAndGenreLike(String titre, String genre);
    List<Jeu> findByTitreLikeAndPlateformeLike(String titre, String plateforme);
    List<Jeu> findByGenreLikeAndPlateformeLike(String genre, String plateforme);
    
    // Récupérer les genres et plateformes distincts
    @Query("SELECT DISTINCT j.genre FROM Jeu j ORDER BY j.genre")
    List<String> findAllGenres();
    
    @Query("SELECT DISTINCT j.plateforme FROM Jeu j ORDER BY j.plateforme")
    List<String> findAllPlateformes();
    
    // Requête pour réinitialiser la séquence d'ID après la valeur maximale actuelle
    @Modifying
    @Query(value = "ALTER TABLE jeux ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM jeux)", nativeQuery = true)
    void resetIdSequence();
}