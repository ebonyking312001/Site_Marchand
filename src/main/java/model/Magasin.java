package model;

public class Magasin {
	
	private String adresseMagasin;
	private String heureFermetureMagasin;
	private String heureOuvertureMagasin;
	private int idMagasin;
	private String nomMagasin;
	
	public Magasin(int IdMagasin, String NomMagasin, String AdresseMagasin, String HeureOuvertureMagasin, String HeureFermetureMagasin) {
		this.adresseMagasin=AdresseMagasin;
		this.heureFermetureMagasin=HeureFermetureMagasin;
		this.heureOuvertureMagasin=HeureOuvertureMagasin;
		this.idMagasin=IdMagasin;
		this.nomMagasin=NomMagasin;
	}
	
	public Magasin() {
		super();
	}

	public String getAdresseMagasin() {
		return adresseMagasin;
	}

	public void setAdresseMagasin(String adresseMagasin) {
		this.adresseMagasin = adresseMagasin;
	}

	public String getHeureFermetureMagasin() {
		return heureFermetureMagasin;
	}

	public void setHeureFermetureMagasin(String heureFermetureMagasin) {
		this.heureFermetureMagasin = heureFermetureMagasin;
	}

	public String getHeureOuvertureMagasin() {
		return heureOuvertureMagasin;
	}

	public void setHeureOuvertureMagasin(String heureOuvertureMagasin) {
		this.heureOuvertureMagasin = heureOuvertureMagasin;
	}

	public int getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(int idMagasin) {
		this.idMagasin = idMagasin;
	}

	public String getNomMagasin() {
		return nomMagasin;
	}

	public void setNomMagasin(String nomMagasin) {
		this.nomMagasin = nomMagasin;
	}

	@Override
	public String toString() {
		return "Magasin [AdresseMagasin=" + adresseMagasin + ", HeureFermetureMagasin=" + heureFermetureMagasin
				+ ", HeureOuvertureMagasin=" + heureOuvertureMagasin + ", IdMagasin=" + idMagasin + ", NomMagasin="
				+ nomMagasin + "]";
	}

}
