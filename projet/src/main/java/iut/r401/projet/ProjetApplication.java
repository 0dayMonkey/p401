package iut.r401.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import iut.r401.projet.service.JeuService;

@SpringBootApplication
public class ProjetApplication {
    
    @Autowired
    private JeuService jeuService;

    public static void main(String[] args) {
        SpringApplication.run(ProjetApplication.class, args);
    }
    
    // Cette méthode s'exécute au démarrage de l'application
    @Bean
    public CommandLineRunner initialiserApplication() {
        return args -> {
            // Réinitialiser la séquence d'ID au démarrage
            jeuService.initialiserSequence();
            System.out.println("Séquence d'identifiants réinitialisée avec succès!");
        };
    }
}