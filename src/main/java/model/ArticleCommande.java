package model;

public class ArticleCommande extends Article {
	private int qteCom;
	private boolean estValide;
	
	public ArticleCommande(int EAN, String vignetteArticle, float prixUnitaireArticle, String nutriscoreArticle,
			String libelleArticle, float poidsArticle, float prixKgArticle, String descriptionCourteArticle,
			String descriptionLongueArticle, String fournisseurArticle, String marque, int promoArticle, int idRayon,
			int idCategorie,int idTypeProduit,int qteCom,boolean estValide) {
		super(EAN, vignetteArticle, prixUnitaireArticle, nutriscoreArticle, libelleArticle, poidsArticle, prixKgArticle,
				descriptionCourteArticle, descriptionLongueArticle, fournisseurArticle, marque, promoArticle, idRayon,
				idCategorie,idTypeProduit);
		this.qteCom=qteCom;
		this.estValide=estValide;
	}

	public int getQteCom() {
		return qteCom;
	}

	public void setQteCom(int qteCom) {
		this.qteCom = qteCom;
	}

	public boolean isEstValide() {
		return estValide;
	}

	public void setEstValide(boolean estValide) {
		this.estValide = estValide;
	}
	

}
