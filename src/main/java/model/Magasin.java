package model;

public class Magasin {
	
	private String AdresseMagasin;
	private String HeureFermetureMagasin;
	private String HeureOuvertureMagasin;
	private int IdMagasin;
	private String NomMagasin;
	
	public Magasin(int IdMagasin, String NomMagasin, String AdresseMagasin, String HeureOuvertureMagasin, String HeureFermetureMagasin) {
		// TODO Auto-generated constructor stub
		this.AdresseMagasin=AdresseMagasin;
		this.HeureFermetureMagasin=HeureFermetureMagasin;
		this.HeureOuvertureMagasin=HeureOuvertureMagasin;
		this.IdMagasin=IdMagasin;
		this.NomMagasin=NomMagasin;
	}

	public String getAdresseMagasin() {
		return AdresseMagasin;
	}

	public void setAdresseMagasin(String adresseMagasin) {
		AdresseMagasin = adresseMagasin;
	}

	public String getHeureFermetureMagasin() {
		return HeureFermetureMagasin;
	}

	public void setHeureFermetureMagasin(String heureFermetureMagasin) {
		HeureFermetureMagasin = heureFermetureMagasin;
	}

	public String getHeureOuvertureMagasin() {
		return HeureOuvertureMagasin;
	}

	public void setHeureOuvertureMagasin(String heureOuvertureMagasin) {
		HeureOuvertureMagasin = heureOuvertureMagasin;
	}

	public int getIdMagasin() {
		return IdMagasin;
	}

	public void setIdMagasin(int idMagasin) {
		IdMagasin = idMagasin;
	}

	public String getNomMagasin() {
		return NomMagasin;
	}

	public void setNomMagasin(String nomMagasin) {
		NomMagasin = nomMagasin;
	}

	@Override
	public String toString() {
		return "Magasin [AdresseMagasin=" + AdresseMagasin + ", HeureFermetureMagasin=" + HeureFermetureMagasin
				+ ", HeureOuvertureMagasin=" + HeureOuvertureMagasin + ", IdMagasin=" + IdMagasin + ", NomMagasin="
				+ NomMagasin + "]";
	}

}
