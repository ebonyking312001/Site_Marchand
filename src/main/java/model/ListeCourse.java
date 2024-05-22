package model;

public class ListeCourse {

	private int idListe;
	private int idUtilisateur;
	private String nomListe;
	
	public ListeCourse(int idListe, String nomListe, int idUtilisateur) {
		this.idListe = idListe;
		this.idUtilisateur = idUtilisateur;
		this.nomListe=nomListe;
	}

	public int getIdListe() {
		return idListe;
	}

	public void setIdListe(int idListe) {
		this.idListe = idListe;
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
