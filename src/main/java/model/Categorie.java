package model;

public class Categorie {

	private int IdCategorie;
	private String NomCategorie;
	


	// Constructeur
	public Categorie(int IdCategorie, String NomCategorie) {
		this.IdCategorie = IdCategorie;
		this.NomCategorie = NomCategorie;
	}



	@Override
	public String toString() {
		return "Categorie [IdCategorie=" + IdCategorie + ", NomCategorie=" + NomCategorie + "]";
	}



	public int getIdCategorie() {
		return IdCategorie;
	}



	public void setIdCategorie(int idCategorie) {
		IdCategorie = idCategorie;
	}



	public String getNomCategorie() {
		return NomCategorie;
	}



	public void setNomCategorie(String nomCategorie) {
		NomCategorie = nomCategorie;
	}

}

