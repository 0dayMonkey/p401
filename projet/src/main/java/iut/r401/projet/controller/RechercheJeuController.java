package iut.r401.projet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iut.r401.projet.model.Jeu;
import iut.r401.projet.service.JeuService;

@RestController
@RequestMapping("/api/jeux")
@CrossOrigin(origins="*")
public class RechercheJeuController {
    
    @Autowired
    private JeuService jeuService;
    
    @GetMapping("/recherche")
    public ResponseEntity<Object> rechercherJeux(
            @RequestParam(required = false, defaultValue = "") String titre,
            @RequestParam(required = false, defaultValue = "") String genre,
            @RequestParam(required = false, defaultValue = "") String plateforme,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size) {
        
        // Correction : rendre tous les paramètres optionnels avec des valeurs par défaut
        List<Jeu> jeux = jeuService.rechercherJeux(titre, genre, plateforme, page, size);
        List<Jeu> totalJeux = jeuService.rechercherJeux(titre, genre, plateforme);
        
        // Créer un objet pour structurer la réponse
        var response = new Object() {
            public final List<Jeu> content = jeux;
            public final int currentPage = page;
            public final int totalItems = totalJeux.size();
            public final int totalPages = (int) Math.ceil((double) totalJeux.size() / size);
        };
        
        return ResponseEntity.ok(response);
    }
}