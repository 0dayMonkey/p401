package iut.r401.projet.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import iut.r401.projet.dto.UtilisateurJeuDTO;
import iut.r401.projet.model.UtilisateurJeu;
import iut.r401.projet.service.UtilisateurJeuService;

@RestController
@RequestMapping("/api/utilisateur-jeu")
@CrossOrigin(origins="*")
public class UtilisateurController {

    @Autowired
    private UtilisateurJeuService utilisateurJeuService;

    //POST /api/utilisateur-jeu Creer la relation
    @PostMapping
    public ResponseEntity<UtilisateurJeuDTO> ajouterJeu(@RequestBody UtilisateurJeuDTO dto){
        UtilisateurJeu uj=utilisateurJeuService.ajouterJeuABibliotheque(
            dto.getIdUtilisateur(),
            dto.getIdJeu(),
            dto.getStatut(),
            dto.getNote(),
            dto.getCommentaire());
        if(uj==null){
            return ResponseEntity.badRequest().build();
        }
        UtilisateurJeuDTO retour = new UtilisateurJeuDTO(
            uj.getId(),
            uj.getUtilisateur().getId(),
            uj.getJeu().getId(),
            uj.getStatut(),
            uj.getNote(),
            uj.getCommentaire()
        );return ResponseEntity.status(HttpStatus.CREATED).body(retour);
    }

    // GET /api/utilisateur-jeu?utilisateur= 

    @GetMapping
    public List<UtilisateurJeuDTO> voirBibliotheque(@RequestParam("utilisateur") Long idUtilisateur){
        List<UtilisateurJeu> liste=utilisateurJeuService.recupererBibliotheque(idUtilisateur);
        return liste.stream().map(uj->{
            UtilisateurJeuDTO dto = new UtilisateurJeuDTO();
            dto.setId(uj.getId());
            dto.setIdUtilisateur(uj.getUtilisateur().getId());
            dto.setIdJeu(uj.getJeu().getId());
            dto.setStatut(uj.getStatut());
            dto.setNote(uj.getNote());
            dto.setCommentaire(uj.getCommentaire());
            return dto;
        }).collect(Collectors.toList());
    }

    //PUT /api/utilisateur-jeu/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurJeuDTO> modifierUtilisateurJeu(@PathVariable("id") Long idUtilisateurJeu,@RequestBody UtilisateurJeuDTO dto){
        UtilisateurJeu uj=utilisateurJeuService.modifierUtilisateurJeu
        (
            idUtilisateurJeu,
            dto.getStatut(),
            dto.getNote(),
            dto.getCommentaire()
        );
        if(uj==null){
            return ResponseEntity.notFound().build();
        }
        UtilisateurJeuDTO retour=new UtilisateurJeuDTO // CONVERTIR EN DTO
        (
            uj.getId(),
            uj.getUtilisateur().getId(),
            uj.getJeu().getId(),
            uj.getStatut(),
            uj.getNote(),
            uj.getCommentaire()
        );
        return ResponseEntity.ok(retour);
    }

    // DELETE /api/utilisateur-jeu/{id}
    @DeleteMapping("/{id}")
    public void supprimerDeBibliotheque(@PathVariable("id") Long idUtilisateurJeu){
        utilisateurJeuService.supprimerDeBibliotheque(idUtilisateurJeu);
    }
}
