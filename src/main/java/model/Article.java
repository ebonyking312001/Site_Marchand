package model;

public class Article {
    private double EAN;
    private String vignetteArticle;
    private double prixUnitaireArticle;
    private String NutriscoreArticle;
    private String libelleArticle;
    private double poidsArticle;
    private double prixKgArticle;
    private String descriptionCourteArticle;
    private String descriptionLongueArticle;
    private String fournisseurArticle;
    private String marque;
    private int idRayon;

    // Constructeur
    public Article(double EAN, String vignetteArticle, double prixUnitaireArticle,
                   String NutriscoreArticle, String libelleArticle, double poidsArticle, double prixKgArticle,
                   String descriptionCourteArticle, String descriptionLongueArticle, String fournisseurArticle,
                   String marque, int idRayon) {
        this.EAN = EAN;
        this.vignetteArticle = vignetteArticle;
        this.prixUnitaireArticle = prixUnitaireArticle;
        this.NutriscoreArticle = NutriscoreArticle;
        this.libelleArticle = libelleArticle;
        this.poidsArticle = poidsArticle;
        this.prixKgArticle = prixKgArticle;
        this.descriptionCourteArticle = descriptionCourteArticle;
        this.descriptionLongueArticle = descriptionLongueArticle;
        this.fournisseurArticle = fournisseurArticle;
        this.marque = marque;
        this.idRayon = idRayon;
    }

    // Getters et Setters

    public double getEAN() { return EAN; }
    public void setEAN(double EAN) { this.EAN = EAN; }

    public String getVignetteArticle() { return vignetteArticle; }
    public void setVignetteArticle(String vignetteArticle) { this.vignetteArticle = vignetteArticle; }

    public double getPrixUnitaireArticle() { return prixUnitaireArticle; }
    public void setPrixUnitaireArticle(double prixUnitaireArticle) { this.prixUnitaireArticle = prixUnitaireArticle; }

    public String getNutriscoreArticle() { return NutriscoreArticle; }
    public void setNutriscoreArticle(String NutriscoreArticle) { this.NutriscoreArticle = NutriscoreArticle; }

    public String getLibelleArticle() { return libelleArticle; }
    public void setLibelleArticle(String libelleArticle) { this.libelleArticle = libelleArticle; }

    public double getPoidsArticle() { return poidsArticle; }
    public void setPoidsArticle(double poidsArticle) { this.poidsArticle = poidsArticle; }

    public double getPrixKgArticle() { return prixKgArticle; }
    public void setPrixKgArticle(double prixKgArticle) { this.prixKgArticle = prixKgArticle; }

    public String getDescriptionCourteArticle() { return descriptionCourteArticle; }
    public void setDescriptionCourteArticle(String descriptionCourteArticle) { this.descriptionCourteArticle = descriptionCourteArticle; }

    public String getDescriptionLongueArticle() { return descriptionLongueArticle; }
    public void setDescriptionLongueArticle(String descriptionLongueArticle) { this.descriptionLongueArticle = descriptionLongueArticle; }

    public String getFournisseurArticle() { return fournisseurArticle; }
    public void setFournisseurArticle(String fournisseurArticle) { this.fournisseurArticle = fournisseurArticle; }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public int getIdRayon() { return idRayon; }
    public void setIdRayon(int idRayon) { this.idRayon = idRayon; }

    @Override
    public String toString() {
        return "Article{" +
                ", EAN='" + EAN + '\'' +
                ", vignetteArticle='" + vignetteArticle + '\'' +
                ", prixUnitaireArticle=" + prixUnitaireArticle +
                ", NutriscoreArticle='" + NutriscoreArticle + '\'' +
                ", libelleArticle='" + libelleArticle + '\'' +
                ", poidsArticle=" + poidsArticle +
                ", prixKgArticle=" + prixKgArticle +
                ", descriptionCourteArticle='" + descriptionCourteArticle + '\'' +
                ", descriptionLongueArticle='" + descriptionLongueArticle + '\'' +
                ", fournisseurArticle='" + fournisseurArticle + '\'' +
                ", marque='" + marque + '\'' +
                ", idRayon=" + idRayon +
                '}';
    }
}
