package iut.r401.projet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import iut.r401.projet.model.Jeu;
import iut.r401.projet.repository.JeuRepository;

@Service
public class JeuService {

    @Autowired
    private JeuRepository jeuRepository;
    
    // Méthode appelée au démarrage pour réinitialiser la séquence
    @Transactional
    public void initialiserSequence() {
        jeuRepository.resetIdSequence();
    }

    public List<Jeu> listerTousLesJeux(){
        return jeuRepository.findAll();
    }

    public Jeu trouverJeuParId(Long id){
        Optional<Jeu> optJeu=jeuRepository.findById(id);
        if(optJeu.isPresent()) {
            return optJeu.get();
        } else {
            return null;
        }    
    }

    @Transactional
    public Jeu creerJeu(String titre, String genre, String plateforme, String description) {
        // Création d'un nouvel objet Jeu sans spécifier l'ID (laissez la base de données le générer)
        Jeu jeu = new Jeu();
        jeu.setTitre(titre);
        jeu.setGenre(genre);
        jeu.setPlateforme(plateforme);
        jeu.setDescription(description);
        
        try {
            return jeuRepository.save(jeu);
        } catch (Exception e) {
            // En cas d'erreur, essayer de réinitialiser la séquence et réessayer
            jeuRepository.resetIdSequence();
            return jeuRepository.save(jeu);
        }
    }

    @Transactional
    public Jeu modifierJeu(Long id, String titre, String genre, String plateforme, String description) {
        Jeu jeuExistant = trouverJeuParId(id);
        if (jeuExistant == null) {
            return null;
        }
        
        jeuExistant.setTitre(titre);
        jeuExistant.setGenre(genre);
        jeuExistant.setPlateforme(plateforme);
        jeuExistant.setDescription(description);
        return jeuRepository.save(jeuExistant);
    }

    public void supprimerJeu(Long id) {
        jeuRepository.deleteById(id);
    }

    /**
     * Récupère tous les genres distincts de jeux
     * @return Liste des genres uniques
     */
    public List<String> getAllGenres() {
        return jeuRepository.findAllGenres();
    }
    
    /**
     * Récupère toutes les plateformes distinctes de jeux
     * @return Liste des plateformes uniques
     */
    public List<String> getAllPlateformes() {
        return jeuRepository.findAllPlateformes();
    }

    /**
     * Recherche des jeux selon les critères fournis
     * Optimisé pour la recherche en temps réel
     */
    public List<Jeu> rechercherJeux(String titre, String genre, String plateforme){
        // Sanitize inputs - convert empty strings to null
        titre = (titre == null || titre.trim().isEmpty()) ? null : titre.trim();
        genre = (genre == null || genre.trim().isEmpty()) ? null : genre.trim();
        plateforme = (plateforme == null || plateforme.trim().isEmpty()) ? null : plateforme.trim();
        
        // Si aucun critère n'est fourni, retourner tous les jeux
        if (titre == null && genre == null && plateforme == null){
            return jeuRepository.findAll();
        }

        // Récupérer les jeux selon les critères disponibles
        List<Jeu> resultat;
        if (titre != null && genre != null && plateforme != null) {
            // Tous les critères sont fournis
            resultat = jeuRepository.findByTitreLikeAndGenreLikeAndPlateformeLike(
                "%" + titre + "%", "%" + genre + "%", "%" + plateforme + "%"
            );
        } else if (titre != null && genre != null) {
            // Titre et genre seulement
            resultat = jeuRepository.findByTitreLikeAndGenreLike(
                "%" + titre + "%", "%" + genre + "%"
            );
        } else if (titre != null && plateforme != null) {
            // Titre et plateforme seulement
            resultat = jeuRepository.findByTitreLikeAndPlateformeLike(
                "%" + titre + "%", "%" + plateforme + "%"
            );
        } else if (genre != null && plateforme != null) {
            // Genre et plateforme seulement
            resultat = jeuRepository.findByGenreLikeAndPlateformeLike(
                "%" + genre + "%", "%" + plateforme + "%"
            );
        } else if (titre != null) {
            // Titre seulement
            resultat = jeuRepository.findByTitreLike("%" + titre + "%");
        } else if (genre != null) {
            // Genre seulement
            resultat = jeuRepository.findByGenreLike("%" + genre + "%");
        } else {
            // Plateforme seulement
            resultat = jeuRepository.findByPlateformeLike("%" + plateforme + "%");
        }

        return resultat;
    }

    /**
     * Recherche des jeux avec pagination
     * Conçu pour le filtrage en temps réel
     */
    public List<Jeu> rechercherJeux(String titre, String genre, String plateforme, int page, int size) {
        // Obtenir la liste complète des jeux correspondant aux critères
        List<Jeu> allJeux = rechercherJeux(titre, genre, plateforme);
        
        // Calculer l'index de début et de fin pour la pagination côté serveur
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, allJeux.size());
        
        // S'assurer que l'index de début est valide
        if (startIndex >= allJeux.size()) {
            return List.of(); // Retourner une liste vide si on dépasse les limites
        }
        
        // Sous-liste pour la pagination
        return allJeux.subList(startIndex, endIndex);
    }
}