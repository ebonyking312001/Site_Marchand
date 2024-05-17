package model;

public class ArticleCommande extends Article {
	private int qteCom;
	
	public ArticleCommande(int EAN, String vignetteArticle, Float prixUnitaireArticle, String nutriscoreArticle,
			String libelleArticle, Float poidsArticle, Float prixKgArticle, String descriptionCourteArticle,
			String descriptionLongueArticle, String fournisseurArticle, String marque, int promoArticle, int idRayon,
			int idCategorie,int idTypeProduit,int qteCom) {
		super(EAN, vignetteArticle, prixUnitaireArticle, nutriscoreArticle, libelleArticle, poidsArticle, prixKgArticle,
				descriptionCourteArticle, descriptionLongueArticle, fournisseurArticle, marque, promoArticle, idRayon,
				idCategorie,idTypeProduit);
		this.qteCom=qteCom;
	}

	public int getQteCom() {
		return qteCom;
	}

	public void setQteCom(int qteCom) {
		this.qteCom = qteCom;
	}
	

}
