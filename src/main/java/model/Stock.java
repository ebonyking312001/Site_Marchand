package model;

public class Stock {

	private int EAN;
	private int IdMagasin;
	private int qte;
	public Stock(int EAN, int IdMagasin, int qte) {
		// TODO Auto-generated constructor stub
		this.EAN=EAN;
		this.IdMagasin=IdMagasin;
		this.qte=qte;
	}
	public int getEAN() {
		return EAN;
	}
	public void setEAN(int eAN) {
		EAN = eAN;
	}
	public int getIdMagasin() {
		return IdMagasin;
	}
	public void setIdMagasin(int idMagasin) {
		IdMagasin = idMagasin;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	@Override
	public String toString() {
		return "Stock [EAN=" + EAN + ", IdMagasin=" + IdMagasin + ", qte=" + qte + "]";
	}

	
}
