package model;

public class TypeProduit {

	private int IdTypeProduit;
	private String NomTypeProduit;
	
	public TypeProduit(int IdTypeProduit, String NomTypeProduit) {
		// TODO Auto-generated constructor stub
		this.IdTypeProduit=IdTypeProduit;
		this.NomTypeProduit=NomTypeProduit;
	}

	public int getIdTypeProduit() {
		return IdTypeProduit;
	}

	public void setIdTypeProduit(int idTypeProduit) {
		IdTypeProduit = idTypeProduit;
	}

	public String getNomTypeProduit() {
		return NomTypeProduit;
	}

	public void setNomTypeProduit(String nomTypeProduit) {
		NomTypeProduit = nomTypeProduit;
	}

	@Override
	public String toString() {
		return "TypeProduit [IdTypeProduit=" + IdTypeProduit + ", NomTypeProduit=" + NomTypeProduit + "]";
	}

	
}
