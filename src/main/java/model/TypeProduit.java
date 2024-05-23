package model;

public class TypeProduit {

	private int IdTypeProduit;
	private String NomTypeProduit;
	private int IdCategorie;
	
	public TypeProduit(int IdTypeProduit, String NomTypeProduit, int idCategorie) {
		this.IdTypeProduit=IdTypeProduit;
		this.NomTypeProduit=NomTypeProduit;
		this.IdCategorie = idCategorie;
	}

	public int getIdTypeProduit() {
		return IdTypeProduit;
	}

	public void setIdTypeProduit(int idTypeProduit) {
		this.IdTypeProduit = idTypeProduit;
	}

	public String getNomTypeProduit() {
		return NomTypeProduit;
	}

	public void setNomTypeProduit(String nomTypeProduit) {
		this.NomTypeProduit = nomTypeProduit;
	}

	public int getIdCategorie() {
		return IdCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.IdCategorie = idCategorie;
	}

	@Override
	public String toString() {
		return "TypeProduit [IdTypeProduit=" + IdTypeProduit + ", NomTypeProduit=" + NomTypeProduit + "]";
	}

	
}
