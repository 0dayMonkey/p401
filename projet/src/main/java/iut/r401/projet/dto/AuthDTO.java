package iut.r401.projet.dto;

public class AuthDTO {
    //auth fictive
    private String pseudo; 
    private String motDePasse;

    
    public AuthDTO() {
    }
    
    public AuthDTO(String pseudo, String motDePasse) {
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    
}
