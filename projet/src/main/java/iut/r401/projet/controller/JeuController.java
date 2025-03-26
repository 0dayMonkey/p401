package iut.r401.projet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iut.r401.projet.dto.JeuDTO;
import iut.r401.projet.model.Jeu;
import iut.r401.projet.service.JeuService;


@RestController
@RequestMapping("/api/jeux")
@CrossOrigin(origins="*")
public class JeuController {
    @Autowired
    private JeuService serviceJeu;

    //GET /api/jeux
    @GetMapping
    public List<Jeu> listerJeux(){
        return serviceJeu.listerTousLesJeux();
    }

    // GET /api/jeux/{id} 
    @GetMapping("/{id}")
    public ResponseEntity<Jeu> trouverJeu(@PathVariable Long id){
        Jeu jeu=serviceJeu.trouverJeuParId(id);
        if(jeu!=null){
            return ResponseEntity.ok(jeu);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/jeux
    @PostMapping
    public ResponseEntity<Jeu> creerJeu(@RequestBody JeuDTO dto){
        Jeu nouveau=serviceJeu.creerJeu(dto.getTitre(),dto.getGenre(),dto.getPlateforme(),dto.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveau);
    }

    // PUT /api/jeux/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Jeu> modifierJeu(@PathVariable Long id,@RequestBody JeuDTO dto) {
        Jeu jeuModifie=serviceJeu.modifierJeu(id,dto.getTitre(),dto.getGenre(),
            dto.getPlateforme(),dto.getDescription());
        if(jeuModifie!=null){
            return ResponseEntity.ok(jeuModifie);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/jeux/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerJeu(@PathVariable Long id){
        Jeu existe=serviceJeu.trouverJeuParId(id);
        if (existe!=null){
            serviceJeu.supprimerJeu(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    // GET /api/jeux/genres - Récupérer tous les genres distincts
    @GetMapping("/genres")
    public ResponseEntity<List<String>> getAllGenres() {
        return ResponseEntity.ok(serviceJeu.getAllGenres());
    }
    
    // GET /api/jeux/plateformes - Récupérer toutes les plateformes distinctes
    @GetMapping("/plateformes")
    public ResponseEntity<List<String>> getAllPlateformes() {
        return ResponseEntity.ok(serviceJeu.getAllPlateformes());
    }
    
    // Supprimer ou commenter cette méthode qui entre en conflit
    // avec RechercheJeuController#rechercherJeux
    /*
    @GetMapping("/recherche")
    public ResponseEntity<Object> rechercherJeux(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String plateforme,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        
        List<Jeu> jeux = serviceJeu.rechercherJeux(titre, genre, plateforme, page, size);
        List<Jeu> totalJeux = serviceJeu.rechercherJeux(titre, genre, plateforme);
        
        var response = new Object() {
            public final List<Jeu> content = jeux;
            public final int currentPage = page;
            public final int totalItems = totalJeux.size();
            public final int totalPages = (int) Math.ceil((double) totalJeux.size() / size);
        };
        
        return ResponseEntity.ok(response);
    }
    */
}