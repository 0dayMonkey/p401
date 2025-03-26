package iut.r401.projet.dto;

public class UtilisateurJeuDTO {
    private Long id;  
    private Long idUtilisateur;
    private Long idJeu;
    private String statut;
    private int note;
    private String commentaire;
    
    public UtilisateurJeuDTO() {
    }
    public UtilisateurJeuDTO(Long id, Long idUtilisateur, Long idJeu, String statut, int note, String commentaire) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idJeu = idJeu;
        this.statut = statut;
        this.note = note;
        this.commentaire = commentaire;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public Long getIdJeu() {
        return idJeu;
    }
    public void setIdJeu(Long idJeu) {
        this.idJeu = idJeu;
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

    
}
