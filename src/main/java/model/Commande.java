package model;

import java.util.Date;

public class Commande {
	private Date DateRetrait;
	private String EtatCommande;
	private int IdCommande;
	private int IdMagasin;
	private int IdUtilisateur;
	
	public Commande(Date DateRetrait,String EtatCommande,int IdCommande,int IdMagasin,int IdUtilisateur){
		this.DateRetrait=DateRetrait;
		this.EtatCommande=EtatCommande;
		this.IdCommande= IdCommande;
		this.IdMagasin =IdMagasin;
		this.IdUtilisateur= IdUtilisateur;
	}
	
	public Date getDateRetrait() {
		return DateRetrait;
	}
	public void setDateRetrait(Date dateRetrait) {
		DateRetrait = dateRetrait;
	}
	public String getEtatCommande() {
		return EtatCommande;
	}
	public void setEtatCommande(String etatCommande) {
		EtatCommande = etatCommande;
	}
	public int getIdCommande() {
		return IdCommande;
	}
	public void setIdCommande(int idCommande) {
		IdCommande = idCommande;
	}
	public int getIdMagasin() {
		return IdMagasin;
	}
	public void setIdMagasin(int idMagasin) {
		IdMagasin = idMagasin;
	}
	public int getIdUtilisateur() {
		return IdUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		IdUtilisateur = idUtilisateur;
	}
}
