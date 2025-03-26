package iut.r401.projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iut.r401.projet.model.Utilisateur;
import iut.r401.projet.service.AuthService;

@RestController
@RequestMapping("/api/utilisateur")
@CrossOrigin(origins="*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PutMapping("/{id}")
    public Utilisateur updateProfile(@PathVariable Long id,@RequestBody Utilisateur utilisateur) {
        return authService.updateUtilisateur(id,utilisateur);
    }
}
