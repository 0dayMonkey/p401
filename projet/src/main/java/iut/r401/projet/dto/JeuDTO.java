package iut.r401.projet.dto;

public class JeuDTO {

    private String titre;
    private String genre;
    private String plateforme;
    private String description;

    public JeuDTO() {
    }

    public JeuDTO(String titre, String genre, String plateforme, String description) {
        this.titre = titre;
        this.genre = genre;
        this.plateforme = plateforme;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlateforme() {
        return plateforme;
    }
    public void setPlateforme(String plateforme) {
        this.plateforme = plateforme;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
