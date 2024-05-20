package bd;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Article;
import model.ArticleCommande;
import model.Commande;
import model.Magasin;

/**
 * Classe en charge de la base de données.
 */
public class ConnectionMySql {
	/*---------*/
	/* Données */
	/*---------*/

	/*----- Connexion -----*/
	private static Connection cx = null;

	/*----- Données de connexion -----*/
	private static final String URL = "jdbc:mysql://srv1049.hstgr.io:3306/u523250608_projetdai";
	private static final String LOGIN = "u523250608_projetdai";
	private static final String PASSWORD = "Projetdai1$";

	/*----------*/
	/* Méthodes */
	/*----------*/

	/**
	 * BD connexion
	 */
	private static void connexion() throws ClassNotFoundException, SQLException {
		/*----- Chargement du pilote pour la ConnectionMySql -----*/
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			throw new ClassNotFoundException(
					"Exception ConnectionMySql.connexion() - Pilote MySql introuvable - " + ex.getMessage());
		}

		/*----- Ouverture de la connexion -----*/
		try {
			ConnectionMySql.cx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
		} catch (SQLException ex) {
			throw new SQLException(
					"Exception ConnectionMySql.connexion() - Problème de connexion à la base de données - "
							+ ex.getMessage());
		}
	}

	/**
	 * Gets all the artciles.
	 */
	public static ArrayList<Article> afficherArticle() throws ClassNotFoundException, SQLException {
		/*----- Création de la connexion à la base de données -----*/
		ConnectionMySql.connexion();

		/*----- Interrogation de la base -----*/
		ArrayList<Article> liste = new ArrayList<>();

		/*----- Requête SQL -----*/
		String sql = "SELECT * from Articles";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			/*----- Exécution de la requête -----*/
			try (ResultSet rs = st.executeQuery()) {
				/*----- Lecture du contenu du ResultSet -----*/
				while (rs.next()) {
					liste.add(new Article(rs.getInt("EAN"), rs.getString("VignetteArticle"),
							rs.getFloat("PrixUnitaireArticle"), rs.getString("NutriscoreArticle"),
							rs.getString("LibelleArticle"), rs.getFloat("PoidsArticle"), rs.getFloat("PrixKgArticle"),
							rs.getString("DescriptionCourteArticle"), rs.getString("DescriptionLongueArticle"),
							rs.getString("FournisseurArticle"), rs.getString("Marque"), rs.getInt("PromoArticle"),
							rs.getInt("IdRayon"), rs.getInt("IdCategorie"), rs.getInt("IdTypeProduit")));
				}
			}
			st.close();
		} catch (SQLException ex) {

			throw new SQLException("Exception ConnectionMySql.afficherArticle() : Problème SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();
		return liste;
	}

	/**
	 * Gets article by Id.
	 */
	public static Article getArticleById(String idArticle) throws ClassNotFoundException, SQLException {
		ConnectionMySql.connexion();

		Article article = new Article();
		String sql = "SELECT * from Articles Where EAN = ?";

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			st.setString(1, idArticle);
			try (ResultSet rs = st.executeQuery()) {
				// Check if there is at least one row in the ResultSet
				if (rs.next()) {
					article = new Article(rs.getInt("EAN"), rs.getString("VignetteArticle"),
							rs.getFloat("PrixUnitaireArticle"), rs.getString("NutriscoreArticle"),
							rs.getString("LibelleArticle"), rs.getFloat("PoidsArticle"), rs.getFloat("PrixKgArticle"),
							rs.getString("DescriptionCourteArticle"), rs.getString("DescriptionLongueArticle"),
							rs.getString("FournisseurArticle"), rs.getString("Marque"), rs.getInt("PromoArticle"),
							rs.getInt("IdRayon"), rs.getInt("IdCategorie"), rs.getInt("IdTypeProduit"));
				} else {
					// Handle the case where no rows were found
					// For example, you can throw an exception or return null
				}
				st.close();
			} catch (SQLException ex) {
				throw new SQLException(
						"Exception ConnectionMySql.getArticleById() : Problème SQL - " + ex.getMessage());
			}

			st.close();
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getArticleById() : Problème SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();
		return article;
	}

	/**
	 * Gets the articles from the catalogue
	 */
//	public static ArrayList<Article> afficherArticleCatalogue() throws ClassNotFoundException, SQLException {
//		// Cr�er la connexion � la base de donn�es
//		ConnectionMySql.connexion();
//
//		// Liste pour stocker les articles
//		ArrayList<Article> liste = new ArrayList<>();
//
//		// Requ�te SQL
//		String sql = "SELECT * from Articles";
//
//		// Ouverture de l'espace de requ�te
//		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
//			// Ex�cution de la requ�te
//			try (ResultSet rs = st.executeQuery()) {
//				// Lecture du contenu du ResultSet
//				while (rs.next()) {
//					int EAN = rs.getInt("EAN");
//					String vignetteArticle = rs.getString("VignetteArticle");
//					float prixUnitaireArticle = rs.getFloat("PrixUnitaireArticle");
//					String NutriscoreArticle = rs.getString("NutriscoreArticle");
//					String libelleArticle = rs.getString("LibelleArticle");
//					float poidsArticle = rs.getFloat("PoidsArticle");
//					float prixKgArticle = rs.getFloat("PrixKgArticle");
//					String descriptionCourteArticle = rs.getString("DescriptionCourteArticle");
//					String descriptionLongueArticle = rs.getString("DescriptionLongueArticle");
//					String fournisseurArticle = rs.getString("FournisseurArticle");
//					String marque = rs.getString("Marque");
//					int promoArticle = rs.getInt("PromoArticle");
//					int idRayon = rs.getInt("IdRayon");
//					int idCategorie = rs.getInt("IdCategorie");
//					int idTypeProduit = rs.getInt("IdTypeProduit");
//
//					// Cr�er un nouvel article et l'ajouter � la liste
//					Article article = new Article(EAN, vignetteArticle, prixUnitaireArticle, NutriscoreArticle,
//							libelleArticle, poidsArticle, prixKgArticle, descriptionCourteArticle,
//							descriptionLongueArticle, fournisseurArticle, marque, promoArticle, idRayon, idCategorie,
//							idTypeProduit);
//					liste.add(article);
//					st.close();
//				}
//			}
//		} catch (SQLException ex) {
//			throw new SQLException(
//					"Exception ConnectionMySql.afficherArticleCatalogue() : Probl�me SQL - " + ex.getMessage());
//		}
//		ConnectionMySql.cx.close();
//
//		return liste;
//	}

	/**
	 * Gets articles from the search box
	 */
	public static ArrayList<Article> chercher(String motSaisi) throws ClassNotFoundException, SQLException {
		/*----- Création de la connexion à la base de données -----*/
		ConnectionMySql.connexion();

		/*----- Interrogation de la base -----*/
		ArrayList<Article> liste = new ArrayList<>();

		/*----- Requête SQL -----*/
		String sql = "SELECT * FROM Articles WHERE Marque LIKE ? OR LibelleArticle LIKE ?";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {

			/*----- Exécution de la requête -----*/
			// Trouver tous les mots qui contiennent la séquence de caractères de motsaisi
			st.setString(1, "%" + motSaisi + "%");
			st.setString(2, "%" + motSaisi + "%");

			try (ResultSet rs = st.executeQuery()) {
				/*----- Lecture du contenu du ResultSet -----*/
				liste = resToArticles(rs);
			}
			st.close();
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.chercher() : Problème SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();

		return liste;
	}

	/**
	 * Upload articles to DB
	 */
	public static void insererArticle(Article article) throws Exception {

		// Cr�er la connexion � la base de donn�es si elle n'est pas d�j�
		// �tablie
		ConnectionMySql.connexion();

		// Requ te SQL d'insertion
		String sql = "INSERT INTO Articles (EAN, VignetteArticle, PrixUnitaireArticle, NutriscoreArticle, LibelleArticle, PoidsArticle, PrixKgArticle, DescriptionCourteArticle, DescriptionLongueArticle, FournisseurArticle, Marque, PromoArticle, IdRayon, IdCategorie, IdTypeProduit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement st = cx.prepareStatement(sql)) {
			// Assigner les valeurs des param tres de la requ te
			st.setDouble(1, article.getEAN());
			st.setString(2, article.getVignetteArticle());
			st.setDouble(3, article.getPrixUnitaireArticle());
			st.setString(4, article.getNutriscoreArticle());
			st.setString(5, article.getLibelleArticle());
			st.setDouble(6, article.getPoidsArticle());
			st.setDouble(7, article.getPrixKgArticle());
			st.setString(8, article.getDescriptionCourteArticle());
			st.setString(9, article.getDescriptionLongueArticle());
			st.setString(10, article.getFournisseurArticle());
			st.setString(11, article.getMarque());
			st.setInt(12, article.getPromoArticle());
			st.setInt(13, article.getIdRayon());
			st.setInt(14, article.getIdCategorie());
			st.setInt(15, article.getIdTypeProduit());

			// Ex cuter la requ te
			st.executeUpdate();
			st.close();
		} catch (SQLException sqle) {
			throw new Exception("Erreur lors de l'insertion de l'article : " + sqle.getMessage());
		}
		ConnectionMySql.cx.close();

	}

	public static ArrayList<Article> resToArticles(ResultSet rs) throws SQLException {
		ArrayList<Article> liste = new ArrayList<>();
		while (rs.next()) {
			Article a = new Article(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getString(5),
					rs.getFloat(6), rs.getFloat(7), rs.getString(8), rs.getString(9), rs.getString(10),
					rs.getString(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15));

			liste.add(a);
		}
		return liste;
	}

	/**
	 * Gets orders by state
	 */
	public static ArrayList<Commande> panierCommande(String commandeEtat) throws ClassNotFoundException, SQLException {
		ArrayList<Commande> liste = new ArrayList<>();

		/*----- Création de la connexion à la base de données -----*/
		ConnectionMySql.connexion();

		/*----- Requête SQL -----*/
		String sql = "SELECT * FROM Commandes WHERE EtatCommande LIKE ?";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {

			/*----- Exécution de la requête -----*/
			// Trouver tous les mots qui contiennent la séquence de caractères de motsaisi
			st.setString(1, commandeEtat);

			try (ResultSet rs = st.executeQuery()) {
				/*----- Lecture du contenu du ResultSet -----*/
				liste = resToCommandes(rs);
			}

		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.panierCommande() : Problème SQL - " + ex.getMessage());
		}
		cx.close();
		return liste;

	}

	public static ArrayList<Commande> resToCommandes(ResultSet rs) throws SQLException {
		ArrayList<Commande> liste = new ArrayList<>();
		while (rs.next()) {
			Commande c = new Commande(rs.getDate("DateRetrait"), rs.getString("EtatCommande"), rs.getInt("IdCommande"),
					rs.getInt("IdMagasin"), rs.getInt("IdUtilisateur"));
			liste.add(c);
		}
		return liste;
	}

	/**
	 * Gets details by order Id
	 */
	public static ArrayList<ArticleCommande> DetailCommande(String idCom) throws ClassNotFoundException, SQLException {
		ArrayList<ArticleCommande> liste = new ArrayList<>();

		/*----- Création de la connexion à la base de données -----*/
		ConnectionMySql.connexion();

		/*----- Requête SQL -----*/
		String sql = "SELECT Articles.*,qteCom " + "FROM Articles,Articles_Commandes "
				+ "WHERE Articles_Commandes.EAN=Articles.EAN " + "AND IdCommande=?";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {

			/*----- Exécution de la requête -----*/
			// Trouver tous les mots qui contiennent la séquence de caractères de motsaisi
			st.setInt(1, Integer.parseInt(idCom));

			try (ResultSet rs = st.executeQuery()) {
				/*----- Lecture du contenu du ResultSet -----*/
				while (rs.next()) {
					ArticleCommande ac = new ArticleCommande(rs.getInt(1), rs.getString(2), rs.getFloat(3),
							rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getFloat(7), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13),
							rs.getInt(14), rs.getInt(15), rs.getInt(16));
					liste.add(ac);
				}
			}

		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.DetailCommande() : Problème SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();
		return liste;

	}

	/**
	 * Gets all magasins info
	 */
	public static ArrayList<Magasin> getAllMagasins() throws ClassNotFoundException, SQLException {
		// Cr�er la connexion � la base de donn�es
		ConnectionMySql.connexion();

		// Liste pour stocker les articles
		ArrayList<Magasin> listeMag = new ArrayList<>();

		// Requ�te SQL
		String sql = "SELECT * from Magasins";

		// Ouverture de l'espace de requ�te
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			// Ex�cution de la requ�te
			try (ResultSet rs = st.executeQuery()) {
				// Lecture du contenu du ResultSet
				while (rs.next()) {
					String adresseMagasin = rs.getString("AdresseMagasin");
					String heureFermetureMagasin = rs.getTime("HeureFermetureMagasin").toString();
					String heureOuvertureMagasin = rs.getTime("HeureOuvertureMagasin").toString();
					int idMagasin = rs.getInt("IdMagasin");
					String nomMagasin = rs.getString("NomMagasin");

					// Cr�er un nouvel article et l'ajouter � la liste
					Magasin mag = new Magasin(idMagasin, nomMagasin, adresseMagasin, heureOuvertureMagasin,
							heureFermetureMagasin);
					listeMag.add(mag);
				}
			}
			st.close();
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getAllMagasins() : Probl�me SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();

		return listeMag;
	}

	/**
	 * Gets the opening in a journey by Magasin name
	 */
	public static String getOpeningByMagasinName(String nomM) throws ClassNotFoundException, SQLException {
		ConnectionMySql.connexion();

		String horaire = "";

		String sql = "SELECT HeureOuvertureMagasin, HeureFermetureMagasin from Magasins Where NomMagasin = ?";

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			st.setString(1, nomM);
			try (ResultSet rs = st.executeQuery()) {
				// Check if there is at least one row in the ResultSet
				if (rs.next()) {
					horaire = rs.getString("HeureOuvertureMagasin") + " - " + rs.getString("HeureFermetureMagasin");
				} else {
					// Handle the case where no rows were found
					// For example, you can throw an exception or return null
				}
				st.close();
			} catch (SQLException ex) {
				throw new SQLException(
						"Exception ConnectionMySql.getOpeningByMagasinName() : Problème SQL - " + ex.getMessage());
			}

			st.close();
		} catch (SQLException ex) {
			throw new SQLException(
					"Exception ConnectionMySql.getOpeningByMagasinName() : Problème SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();

		System.out.println(horaire);
		return horaire;
	}

	/**
	 * Gets magasin by Id
	 */
	public static Magasin getMagasinById(int idMag) throws ClassNotFoundException, SQLException {
		// Cr�er la connexion � la base de donn�es
		ConnectionMySql.connexion();

		Magasin magasin = new Magasin();
		String sql = "SELECT * from Magasins Where IdMagasin = ?";

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			st.setInt(1, idMag);
			try (ResultSet rs = st.executeQuery()) {
				// Check if there is at least one row in the ResultSet
				if (rs.next()) {
					magasin = new Magasin(rs.getInt("IdMagasin"), rs.getString("NomMagasin"),
							rs.getString("AdresseMagasin"), rs.getString("HeureOuvertureMagasin"),
							rs.getString("HeureFermetureMagasin"));
				} else {
					// Handle the case where no rows were found
					// For example, you can throw an exception or return null
				}
				st.close();
			} catch (SQLException ex) {
				throw new SQLException(
						"Exception ConnectionMySql.getMagasinById() : Problème SQL - " + ex.getMessage());
			}

			st.close();
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getMagasinById() : Problème SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();
		return magasin;
	}

	/**
	 * Gets all the hours opened by magasin Id
	 */
	public static ArrayList<String> getHoursOpenedByMagasinId(int magasinId) {
		Magasin mag = new Magasin();
		try {
			mag = getMagasinById(magasinId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String hOuv = mag.getHeureOuvertureMagasin().toString(), hFer = mag.getHeureFermetureMagasin().toString(), hO,
				hF, minuteO, minuteF;

		ArrayList<String> heuresCreneaux = new ArrayList<>();

		hO = hOuv.substring(0, hOuv.length() - 3);
		hF = hFer.substring(0, hFer.length() - 3);

		minuteO = hO.substring(hO.lastIndexOf(":") + 1);
		minuteF = hF.substring(hF.lastIndexOf(":") + 1);

		boolean passOneTour = false;

		for (int iO = Integer.parseInt(hO.substring(0, hO.length() - 3)); iO < Integer
				.parseInt(hF.substring(0, hF.length() - 3)); iO++) {
			if (!passOneTour) {
				if (Integer.parseInt(minuteO) == 0) {
					heuresCreneaux.add(iO <= 9 ? "0" + Integer.toString(iO) + ":00 - " + Integer.toString(iO) + ":15"
							: Integer.toString(iO) + ":00 - " + Integer.toString(iO) + ":15");
					heuresCreneaux.add(iO <= 9 ? "0" + Integer.toString(iO) + ":15 - " + Integer.toString(iO) + ":30"
							: Integer.toString(iO) + ":15 - " + Integer.toString(iO) + ":30");
					heuresCreneaux.add(iO <= 9 ? "0" + Integer.toString(iO) + ":30 - " + Integer.toString(iO) + ":45"
							: Integer.toString(iO) + ":30 - " + Integer.toString(iO) + ":45");
					heuresCreneaux
							.add(iO <= 9 ? "0" + Integer.toString(iO) + ":45 - " + Integer.toString(iO + 1) + ":00"
									: Integer.toString(iO) + ":45 - " + Integer.toString(iO + 1) + ":00");
				} else if (Integer.parseInt(minuteO) == 15) {
					heuresCreneaux.add(iO <= 9 ? "0" + Integer.toString(iO) + ":15 - " + Integer.toString(iO) + ":30"
							: Integer.toString(iO) + ":15 - " + Integer.toString(iO) + ":30");
					heuresCreneaux.add(iO <= 9 ? "0" + Integer.toString(iO) + ":30 - " + Integer.toString(iO) + ":45"
							: Integer.toString(iO) + ":30 - " + Integer.toString(iO) + ":45");
					heuresCreneaux
							.add(iO <= 9 ? "0" + Integer.toString(iO) + ":45 - " + Integer.toString(iO + 1) + ":00"
									: Integer.toString(iO) + ":45 - " + Integer.toString(iO + 1) + ":00");
				} else if (Integer.parseInt(minuteO) == 30) {
					heuresCreneaux.add(iO <= 9 ? "0" + Integer.toString(iO) + ":30 - " + Integer.toString(iO) + ":45"
							: Integer.toString(iO) + ":30 - " + Integer.toString(iO) + ":45");
					heuresCreneaux
							.add(iO <= 9 ? "0" + Integer.toString(iO) + ":45 - " + Integer.toString(iO + 1) + ":00"
									: Integer.toString(iO) + ":45 - " + Integer.toString(iO + 1) + ":00");
				} else if (Integer.parseInt(minuteO) == 45) {
					heuresCreneaux
							.add(iO <= 9 ? "0" + Integer.toString(iO) + ":45 - " + Integer.toString(iO + 1) + ":00"
									: Integer.toString(iO) + ":45 - " + Integer.toString(iO + 1) + ":00");
				}
			} else {
				heuresCreneaux.add(Integer.toString(iO) + ":00 - " + Integer.toString(iO) + ":15");
				heuresCreneaux.add(Integer.toString(iO) + ":15 - " + Integer.toString(iO) + ":30");
				heuresCreneaux.add(Integer.toString(iO) + ":30 - " + Integer.toString(iO) + ":45");
				heuresCreneaux.add(Integer.toString(iO) + ":45 - " + Integer.toString(iO + 1) + ":00");
			}
		}

		if (Integer.parseInt(minuteF) > 0) {
			if (Integer.parseInt(minuteF) >= 15) {
				heuresCreneaux
						.add(hF.substring(0, hF.length() - 3) + ":00 - " + hF.substring(0, hF.length() - 3) + ":15");
			}
			if (Integer.parseInt(minuteF) >= 30) {
				heuresCreneaux
						.add(hF.substring(0, hF.length() - 3) + ":15 - " + hF.substring(0, hF.length() - 3) + ":30");
			}
			if (Integer.parseInt(minuteF) >= 45) {
				heuresCreneaux
						.add(hF.substring(0, hF.length() - 3) + ":30 - " + hF.substring(0, hF.length() - 3) + ":45");
			}
		}

		return heuresCreneaux;
	}

	/*----------------------------*/
	/* Programme principal (test) */
	/*----------------------------*/

	public static void main(String[] s) throws Exception {
//		getHoursOpenedByMagasinId(1);
//		getOpeningByMagasinName("ElectroPlus");
	}
} /*----- Fin de la classe ConnectionMySql -----*/
