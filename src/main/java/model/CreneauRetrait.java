package model;

public class CreneauRetrait {
	
	private String DebutCreneau;
	private String FinCreneau;
	private int IdCreneau;

	public CreneauRetrait() {}
	
	public CreneauRetrait(int IdCreneau, String DebutCreneau, String FinCreneau) {
		this.DebutCreneau = DebutCreneau;
		this.FinCreneau = FinCreneau;
		this.IdCreneau = IdCreneau;	
	}

	public String getDebutCreneau() {
		return DebutCreneau;
	}

	public void setDebutCreneau(String debutCreneau) {
		DebutCreneau = debutCreneau;
	}

	public String getFinCreneau() {
		return FinCreneau;
	}

	public void setFinCreneau(String finCreneau) {
		FinCreneau = finCreneau;
	}

	public int getIdCreneau() {
		return IdCreneau;
	}

	public void setIdCreneau(int idCreneau) {
		IdCreneau = idCreneau;
	}
	
}
