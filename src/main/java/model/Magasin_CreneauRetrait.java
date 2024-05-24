package model;

import java.sql.Time;

public class Magasin_CreneauRetrait {

	private int IdCreneau;
	private int IdMagasin;
	private String NomMagasin;
	private Time DebutCreneau;
	private Time FinCreneau;
	private int NbDispoCreneau;
	
	public Magasin_CreneauRetrait(int IdCreneau, int IdMagasin) {
		// TODO Auto-generated constructor stub
		this.IdCreneau=IdCreneau;
		this.IdMagasin=IdMagasin;
	}

	public String getNomMagasin() {
		return NomMagasin;
	}

	public void setNomMagasin(String nomMagasin) {
		NomMagasin = nomMagasin;
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

	public int getNbDispoCreneau() {
		return NbDispoCreneau;
	}

	public void setNbDispoCreneau(int nbDispoCreneau) {
		NbDispoCreneau = nbDispoCreneau;
	}

	public int getIdCreneau() {
		return IdCreneau;
	}

	public void setIdCreneau(int idCreneau) {
		IdCreneau = idCreneau;
	}

	public int getIdMagasin() {
		return IdMagasin;
	}

	public void setIdMagasin(int idMagasin) {
		IdMagasin = idMagasin;
	}

	@Override
	public String toString() {
		return "Magasin_CreneauRetrait [IdCreneau=" + IdCreneau + ", IdMagasin=" + IdMagasin + "]";
	}
	
	/**
	 * for sql requete creneauxdispo
	 * @param idCreneau
	 * @param idMagasin
	 * @param nomMagasin
	 * @param debutCreneau
	 * @param finCreneau
	 * @param nbDispoCreneau
	 */
	public Magasin_CreneauRetrait(int idCreneau, int idMagasin, String nomMagasin, Time debutCreneau, Time finCreneau,
			int nbDispoCreneau) {
		super();
		IdCreneau = idCreneau;
		IdMagasin = idMagasin;
		NomMagasin = nomMagasin;
		DebutCreneau = debutCreneau;
		FinCreneau = finCreneau;
		NbDispoCreneau = nbDispoCreneau;
	}
	
	

}
