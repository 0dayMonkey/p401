package iut.r401.projet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="utilisateurs")
public class Utilisateur{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String pseudo;
    private String email;

    public Utilisateur() {
    }

    public Utilisateur(String pseudo, String email, String motDePasse, String role) {
        this.pseudo = pseudo;
        this.email = email;
    }

    public Long getId() { 
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
         this.email = email;
    }


}
