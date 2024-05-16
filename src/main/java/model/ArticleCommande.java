package model;

public class ArticleCommande extends Article {
	private int qteCom;

	public ArticleCommande(int EAN, String vignetteArticle, Float prixUnitaireArticle, String nutriscoreArticle,
			String libelleArticle, Float poidsArticle, Float prixKgArticle, String descriptionCourteArticle,
			String descriptionLongueArticle, String fournisseurArticle, String marque, int idRayon,int qteCom) {
		super(EAN, vignetteArticle, prixUnitaireArticle, nutriscoreArticle, libelleArticle, poidsArticle, prixKgArticle,
				descriptionCourteArticle, descriptionLongueArticle, fournisseurArticle, marque, idRayon);
		this.qteCom=qteCom;
	}
	
	public ArticleCommande(Article a,int qteCom) {
		super(a.getEAN(), 
				a.getVignetteArticle(), 
				a.getPrixUnitaireArticle(), 
				a.getNutriscoreArticle(), 
				a.getLibelleArticle(), 
				a.getPoidsArticle(), 
				a.getPrixKgArticle(),
				a.getDescriptionCourteArticle(), 
				a.getDescriptionLongueArticle(), 
				a.getFournisseurArticle(), 
				a.getMarque(), 
				a.getIdRayon()
				);
		this.qteCom=qteCom;
	}

	public int getQteCom() {
		return qteCom;
	}

	public void setQteCom(int qteCom) {
		this.qteCom = qteCom;
	}
	

}
