package model;

public class TypeProduit {

	private int idTypeProduit;
	private String nomTypeProduit;
	private int idCategorie;
	
	public TypeProduit(int IdTypeProduit, String NomTypeProduit, int idCategorie) {
		this.idTypeProduit=IdTypeProduit;
		this.nomTypeProduit=NomTypeProduit;
		this.idCategorie = idCategorie;
	}

	public int getIdTypeProduit() {
		return idTypeProduit;
	}

	public void setIdTypeProduit(int idTypeProduit) {
		this.idTypeProduit = idTypeProduit;
	}

	public String getNomTypeProduit() {
		return nomTypeProduit;
	}

	public void setNomTypeProduit(String nomTypeProduit) {
		this.nomTypeProduit = nomTypeProduit;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	@Override
	public String toString() {
		return "TypeProduit [IdTypeProduit=" + idTypeProduit + ", NomTypeProduit=" + nomTypeProduit + "]";
	}

	
}
