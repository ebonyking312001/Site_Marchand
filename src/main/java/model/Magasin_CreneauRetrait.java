package model;

public class Magasin_CreneauRetrait {

	private int IdCreneau;
	private int IdMagasin;
	
	public Magasin_CreneauRetrait(int IdCreneau, int IdMagasin) {
		// TODO Auto-generated constructor stub
		this.IdCreneau=IdCreneau;
		this.IdMagasin=IdMagasin;
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
	
	

}
