package model;

public class TypeProduit {

	private int idTypeProduit;
	private String nomTypeProduit;
	
	public TypeProduit(int idTypeProduit, String nomTypeProduit) {
		// TODO Auto-generated constructor stub
		this.idTypeProduit=idTypeProduit;
		this.nomTypeProduit=nomTypeProduit;
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

	@Override
	public String toString() {
		return "TypeProduit [IdTypeProduit=" + idTypeProduit + ", NomTypeProduit=" + nomTypeProduit + "]";
	}

	
}
