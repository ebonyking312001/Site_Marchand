package model;

public class TypeProduit {

	private int idTypeProduit;
	private String nomTypeProduit;
	private int idCategorie;
	
	public TypeProduit(int idTypeProduit, String nomTypeProduit, int idCategorie) {
		this.idTypeProduit=idTypeProduit;
		this.nomTypeProduit=nomTypeProduit;
		this.idCategorie=idCategorie;
	}

	public int getIdTypeProduit() {
		return idTypeProduit;
	}

	public void setIdTypeProduit(int idTypeProduit) {
		idTypeProduit = idTypeProduit;
	}

	public String getNomTypeProduit() {
		return nomTypeProduit;
	}

	public void setNomTypeProduit(String nomTypeProduit) {
		nomTypeProduit = nomTypeProduit;
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
