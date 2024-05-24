package model;

import java.util.ArrayList;

public class ContenuListe {

	private int idListe;
	private int EAN;
	private int idTypeProduit;
	private int quantite;
	private String nomTypeProduit;
	private String libelleArticle;
	private String marque;


	
	public ContenuListe(int idListe, int EAN, int idTypeProduit, int quantite) {
		this.EAN = EAN;
		this.idListe = idListe;
		this.idTypeProduit = idTypeProduit;
		this.quantite=quantite;
	}

	public int getIdListe() {
		return idListe;
	}

	public void setIdListe(int idListe) {
		this.idListe = idListe;
	}

	public int getEAN() {
		return EAN;
	}

	public void setEAN(int eAN) {
		EAN = eAN;
	}

	public int getIdTypeProduit() {
		return idTypeProduit;
	}

	public void setIdTypeProduit(int idTypeProduit) {
		this.idTypeProduit = idTypeProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getNomTypeProduit() {
		return nomTypeProduit;
	}

	public void setNomTypeProduit(String string) {
		this.nomTypeProduit = string;
	}

	public String getLibelleArticle() {
		return libelleArticle;
	}

	public void setLibelleArticle(String libelleArticle) {
		this.libelleArticle = libelleArticle;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}
	

	
	
	
	
	

}
