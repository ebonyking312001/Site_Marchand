package model;

public class Article {
	private int EAN;
	private String vignetteArticle;
	private float prixUnitaireArticle;
	private String nutriscoreArticle;
	private String libelleArticle;
	private float poidsArticle;
	private float prixKgArticle;
	private String descriptionCourteArticle;
	private String descriptionLongueArticle;
	private String fournisseurArticle;
	private String marque;
	private int promoArticle;
	private int idRayon;
	private int quantite;
	private int idCategorie;
	private int idTypeProduit;
	private String nomCategorie;


	// Constructeur
	public Article(int EAN, String vignetteArticle, float prixUnitaireArticle, String nutriscoreArticle,
			String libelleArticle, float poidsArticle, float prixKgArticle, String descriptionCourteArticle,
			String descriptionLongueArticle, String fournisseurArticle, String marque, int promoArticle, int idRayon,int idCategorie, int idTypeProduit) {
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
		this.promoArticle = promoArticle;
		this.idRayon = idRayon;
		this.quantite = 0;
		this.idCategorie=idCategorie;
		this.idTypeProduit=idTypeProduit;
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

	public int getPromoArticle() {
		return promoArticle;
	}



	public void setPromoArticle(int promoArticle) {
		this.promoArticle = promoArticle;
	}



	public String getVignetteArticle() {
		return vignetteArticle;
	}

	public void setVignetteArticle(String vignetteArticle) {
		this.vignetteArticle = vignetteArticle;
	}

	public float getPrixUnitaireArticle() {
		return prixUnitaireArticle;
	}

	public void setPrixUnitaireArticle(float prixUnitaireArticle) {
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

	public float getPoidsArticle() {
		return poidsArticle;
	}

	public void setPoidsArticle(float poidsArticle) {
		this.poidsArticle = poidsArticle;
	}

	public float getPrixKgArticle() {
		return prixKgArticle;
	}

	public void setPrixKgArticle(float prixKgArticle) {
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
	
	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public int getIdTypeProduit() {
		return idTypeProduit;
	}

	public void setIdTypeProduit(int typeProduit) {
		this.idTypeProduit = typeProduit;
	}
	
	public String getNomCategorie() {
	    return nomCategorie;
	}
	  
	public void setNomCategorie(String nomCategorie) {
	    this.nomCategorie = nomCategorie;
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
