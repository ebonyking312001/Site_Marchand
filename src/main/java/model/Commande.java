package model;

import java.sql.Time;
import java.util.Date;

public class Commande {
	private Date DateRetrait;
	private String EtatCommande;
	private int IdCommande;
	private int IdMagasin;
	private int IdUtilisateur;
	private int idCreneau;
	private Time DebutCreneau;
	private Time FinCreneau;
	private String NomMagasin;
	
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
	
	public Time getDebutCreneau() {
		return DebutCreneau;
	}

	public void setDebutCreneau(Time debutCreneau) {
		DebutCreneau = debutCreneau;
	}
	
	public Time getFinCreneau() {
		return FinCreneau;
	}

	public void setFinCreneau(Time finCreneau) {
		FinCreneau = finCreneau;
	}

	public String getNomMagasin() {
		return NomMagasin;
	}

	public void setNomMagasin(String nomMagasin) {
		NomMagasin = nomMagasin;
	}

	public int getIdCreneau() {
		return idCreneau;
	}

	public void setIdCreneau(int idCreneau) {
		this.idCreneau = idCreneau;
	}
	
	/**
	 *  Constructor special pour resToCmd, afficher commandes a preparer
	 */
	public Commande(Date DateRetrait, Time DebutCreneau, int IdCommande, String NomMagasin,int IdUtilisateur,String EtatCommande){
		this.DateRetrait=DateRetrait;
		this.DebutCreneau=DebutCreneau;
		this.IdCommande= IdCommande;
		this.NomMagasin =NomMagasin;
		this.IdUtilisateur=IdUtilisateur;
		this.EtatCommande=EtatCommande;
	}
}
