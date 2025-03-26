package iut.r401.projet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur_jeu")
public class UtilisateurJeu {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String statut;
    private int note;
    private String commentaire;

    //NB : relations a finir
    @ManyToOne
    @JoinColumn(name="utilisateur_id",nullable=false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name="jeu_id",nullable=false)
    private Jeu jeu;

    public UtilisateurJeu() {
    }

    public UtilisateurJeu(String statut, int note, String commentaire, Utilisateur utilisateur, Jeu jeu) {
        this.statut = statut;
        this.note = note;
        this.commentaire = commentaire;
        this.utilisateur = utilisateur;
        this.jeu = jeu;
    }

    public Long getId() {
        return id;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNote() {
         return note;
    }
    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Jeu getJeu() {
        return jeu;
    }
    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }
}
