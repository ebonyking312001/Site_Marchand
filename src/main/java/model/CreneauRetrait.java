package model;

public class CreneauRetrait {
	
	private String DebutCreneau;
	private String FinCreneau;
	private int IdCreneau;
	private int NbDispoCreneau;
	
	public CreneauRetrait(int IdCreneau, String DebutCreneau, String FinCreneau,  int NbDispoCreneau) {
		this.DebutCreneau = DebutCreneau;
		this.FinCreneau = FinCreneau;
		this.IdCreneau = IdCreneau;
		this.NbDispoCreneau = IdCreneau;	
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

	public int getNbDispoCreneau() {
		return NbDispoCreneau;
	}

	public void setNbDispoCreneau(int nbDispoCreneau) {
		NbDispoCreneau = nbDispoCreneau;
	}

	@Override
	public String toString() {
		return "CreneauRetrait [DebutCreneau=" + DebutCreneau + ", FinCreneau=" + FinCreneau + ", IdCreneau="
				+ IdCreneau + ", NbDispoCreneau=" + NbDispoCreneau + "]";
	}
	
}
