package model;

public class Rayon {

	private int IdRayon;
	private String NomRayon;
	public Rayon(int IdRayon, String NomRayon) {
		// TODO Auto-generated constructor stub
		this.IdRayon=IdRayon;
		this.NomRayon=NomRayon;
		
	}
	public int getIdRayon() {
		return IdRayon;
	}
	public void setIdRayon(int idRayon) {
		IdRayon = idRayon;
	}
	public String getNomRayon() {
		return NomRayon;
	}
	public void setNomRayon(String nomRayon) {
		NomRayon = nomRayon;
	}
	@Override
	public String toString() {
		return "Rayon [IdRayon=" + IdRayon + ", NomRayon=" + NomRayon + "]";
	}
	
	

}
