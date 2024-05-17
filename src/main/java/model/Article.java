package model;

public class Article {
	private int EAN;
	private String vignetteArticle;
	private Float prixUnitaireArticle;
	private String nutriscoreArticle;
	private String libelleArticle;
	private Float poidsArticle;
	private Float prixKgArticle;
	private String descriptionCourteArticle;
	private String descriptionLongueArticle;
	private String fournisseurArticle;
	private String marque;
	private int idRayon;
	private int quantite;

	// Constructeur
	public Article(int EAN, String vignetteArticle, Float prixUnitaireArticle, String nutriscoreArticle,
			String libelleArticle, Float poidsArticle, Float prixKgArticle, String descriptionCourteArticle,
			String descriptionLongueArticle, String fournisseurArticle, String marque, int idRayon) {
		super();
		this.EAN = EAN;
		this.vignetteArticle = vignetteArticle;
		this.prixUnitaireArticle = prixUnitaireArticle;
		this.nutriscoreArticle = nutriscoreArticle;
		this.libelleArticle = libelleArticle;
		this.poidsArticle = poidsArticle;
		this.prixKgArticle = prixKgArticle;
		this.descriptionCourteArticle = descriptionCourteArticle;
		this.descriptionLongueArticle = descriptionLongueArticle;
		this.fournisseurArticle = fournisseurArticle;
		this.marque = marque;
		this.idRayon = idRayon;
		this.quantite = 1;
	}

	// Constructor without parameters
	public Article() {
		super();
	}

	// Getters et Setters
	public int getEAN() {
		return EAN;
	}

	public void setEAN(int EAN) {
		this.EAN = EAN;
	}

	public String getVignetteArticle() {
		return vignetteArticle;
	}

	public void setVignetteArticle(String vignetteArticle) {
		this.vignetteArticle = vignetteArticle;
	}

	public Float getPrixUnitaireArticle() {
		return prixUnitaireArticle;
	}

	public void setPrixUnitaireArticle(Float prixUnitaireArticle) {
		this.prixUnitaireArticle = prixUnitaireArticle;
	}

	public String getNutriscoreArticle() {
		return nutriscoreArticle;
	}

	public void setNutriscoreArticle(String nutriscoreArticle) {
		this.nutriscoreArticle = nutriscoreArticle;
	}

	public String getLibelleArticle() {
		return libelleArticle;
	}

	public void setLibelleArticle(String libelleArticle) {
		this.libelleArticle = libelleArticle;
	}

	public Float getPoidsArticle() {
		return poidsArticle;
	}

	public void setPoidsArticle(Float poidsArticle) {
		this.poidsArticle = poidsArticle;
	}

	public Float getPrixKgArticle() {
		return prixKgArticle;
	}

	public void setPrixKgArticle(Float prixKgArticle) {
		this.prixKgArticle = prixKgArticle;
	}

	public String getDescriptionCourteArticle() {
		return descriptionCourteArticle;
	}

	public void setDescriptionCourteArticle(String descriptionCourteArticle) {
		this.descriptionCourteArticle = descriptionCourteArticle;
	}

	public String getDescriptionLongueArticle() {
		return descriptionLongueArticle;
	}

	public void setDescriptionLongueArticle(String descriptionLongueArticle) {
		this.descriptionLongueArticle = descriptionLongueArticle;
	}

	public String getFournisseurArticle() {
		return fournisseurArticle;
	}

	public void setFournisseurArticle(String fournisseurArticle) {
		this.fournisseurArticle = fournisseurArticle;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public int getIdRayon() {
		return idRayon;
	}

	public void setIdRayon(int idRayon) {
		this.idRayon = idRayon;
	}
	
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Override
	public String toString() {

		return "Article [EAN=" + EAN + ", vignetteArticle=" + vignetteArticle + ", prixUnitaireArticle="
				+ prixUnitaireArticle + ", nutriscoreArticle=" + nutriscoreArticle + ", libelleArticle="
				+ libelleArticle + ", poidsArticle=" + poidsArticle + ", prixKgArticle=" + prixKgArticle
				+ ", descriptionCourteArticle=" + descriptionCourteArticle + ", descriptionLongueArticle="
				+ descriptionLongueArticle + ", fournisseurArticle=" + fournisseurArticle + ", marque=" + marque
				+ ", idRayon=" + idRayon + "]";
	}
}
