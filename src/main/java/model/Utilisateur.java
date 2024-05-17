package model;

public class Utilisateur {

	private int IdUtilisateur;
	private String PrenomUtilisateur;
	private String AdresseUtilisateur;
	private String EmailUtilisateur;
	private String NomUtilisateur;
	private int PointsFideliteUtilisateur;
	private String RoleUtilisateur;
	
	public Utilisateur(int IdUtilisateur, String PrenomUtilisateur, String NomUtilisateur, String EmailUtilisateur, String RoleUtilisateur, String AdresseUtilisateur, int PointsFideliteUtilisateur) {
		// TODO Auto-generated constructor stub
		this.IdUtilisateur=IdUtilisateur;
		this.PrenomUtilisateur=PrenomUtilisateur;
		this.NomUtilisateur=NomUtilisateur;
		this.EmailUtilisateur=EmailUtilisateur;
		this.RoleUtilisateur=RoleUtilisateur;
		this.AdresseUtilisateur=AdresseUtilisateur;
		this.PointsFideliteUtilisateur=PointsFideliteUtilisateur;
	}

	public int getIdUtilisateur() {
		return IdUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		IdUtilisateur = idUtilisateur;
	}

	public String getPrenomUtilisateur() {
		return PrenomUtilisateur;
	}

	public void setPrenomUtilisateur(String prenomUtilisateur) {
		PrenomUtilisateur = prenomUtilisateur;
	}

	public String getAdresseUtilisateur() {
		return AdresseUtilisateur;
	}

	public void setAdresseUtilisateur(String adresseUtilisateur) {
		AdresseUtilisateur = adresseUtilisateur;
	}

	public String getEmailUtilisateur() {
		return EmailUtilisateur;
	}

	public void setEmailUtilisateur(String emailUtilisateur) {
		EmailUtilisateur = emailUtilisateur;
	}

	public String getNomUtilisateur() {
		return NomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		NomUtilisateur = nomUtilisateur;
	}

	public int getPointsFideliteUtilisateur() {
		return PointsFideliteUtilisateur;
	}

	public void setPointsFideliteUtilisateur(int pointsFideliteUtilisateur) {
		PointsFideliteUtilisateur = pointsFideliteUtilisateur;
	}

	public String getRoleUtilisateur() {
		return RoleUtilisateur;
	}

	public void setRoleUtilisateur(String roleUtilisateur) {
		RoleUtilisateur = roleUtilisateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [IdUtilisateur=" + IdUtilisateur + ", PrenomUtilisateur=" + PrenomUtilisateur
				+ ", AdresseUtilisateur=" + AdresseUtilisateur + ", EmailUtilisateur=" + EmailUtilisateur
				+ ", NomUtilisateur=" + NomUtilisateur + ", PointsFideliteUtilisateur=" + PointsFideliteUtilisateur
				+ ", RoleUtilisateur=" + RoleUtilisateur + "]";
	}
	
	

}
