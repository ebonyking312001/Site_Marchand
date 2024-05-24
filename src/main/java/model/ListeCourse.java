package model;

import java.util.ArrayList;

public class ListeCourse {

	private int idListe;
	private int idUtilisateur;
	private String nomListe;
	private ArrayList<ContenuListe> contenuTypeProduit;
	private ArrayList<ContenuListe> contenuArticle;
	
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

	public ArrayList<ContenuListe> getContenuTypeProduit() {
		return contenuTypeProduit;
	}

	public ArrayList<ContenuListe> getContenuArticle() {
		return contenuArticle;
	}
	
	public void setContenuTypeProduit(ArrayList<ContenuListe> contenuTypeProduit) {
	    this.contenuTypeProduit = contenuTypeProduit;
	}
	
	public void setContenuArticle(ArrayList<ContenuListe> contenuArticle) {
	    this.contenuArticle = contenuArticle;
	}

}
