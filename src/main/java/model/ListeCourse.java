package model;

public class ListeCourse {

	private int idListe;
	private int idTypeProduit;
	private int idUtilisateur;
	private String nomListe;
	
	public ListeCourse(int idListe, String nomListe, int idTypeProduit, int idUtilisateur) {
		this.idListe = idListe;
		this.idTypeProduit = idTypeProduit;
		this.idUtilisateur = idUtilisateur;
		this.nomListe=nomListe;
	}

	public int getIdListe() {
		return idListe;
	}

	public void setIdListe(int idListe) {
		this.idListe = idListe;
	}

	public int getIdTypeProduit() {
		return idTypeProduit;
	}

	public void setIdTypeProduit(int idTypeProduit) {
		this.idTypeProduit = idTypeProduit;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNomListe() {
		return nomListe;
	}

	public void setNomListe(String nomListe) {
		this.nomListe = nomListe;
	}

}
