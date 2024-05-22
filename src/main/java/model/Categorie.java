package model;

public class Categorie {

	private int idCategorie;
	private String nomCategorie;
	


	// Constructeur
	public Categorie(int IdCategorie, String NomCategorie) {
		this.idCategorie = IdCategorie;
		this.nomCategorie = NomCategorie;
	}



	@Override
	public String toString() {
		return "Categorie [IdCategorie=" + idCategorie + ", NomCategorie=" + nomCategorie + "]";
	}



	public int getIdCategorie() {
		return idCategorie;
	}



	public void setIdCategorie(int idCategorie) {
		idCategorie = idCategorie;
	}



	public String getNomCategorie() {
		return nomCategorie;
	}



	public void setNomCategorie(String nomCategorie) {
		nomCategorie = nomCategorie;
	}

}

