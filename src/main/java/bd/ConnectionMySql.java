package bd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Article;
import model.ArticleCommande;
import model.Categorie;
import model.Commande;
import model.Magasin;
import model.TypeProduit;
import model.ContenuListe;
import model.ListeCourse;
import model.CreneauRetrait;

/**
 * Classe en charge de la base de donnÃ©es.
 */
public class ConnectionMySql {
	/*---------*/
	/* DonnÃ©es */
	/*---------*/

	/*----- Connexion -----*/
	private static Connection cx = null;

	/*----- DonnÃ©es de connexion -----*/
	private static final String URL = "jdbc:mysql://srv1049.hstgr.io:3306/u523250608_projetdai";
	private static final String LOGIN = "u523250608_projetdai";
	private static final String PASSWORD = "Projetdai1$";

	/*----------*/
	/* MÃ©thodes */
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
					"Exception ConnectionMySql.connexion() - ProblÃ¨me de connexion Ã  la base de donnÃ©es - "
							+ ex.getMessage());
		}
	}

	/**
	 * Gets all the artciles.
	 */
	public static ArrayList<Article> afficherArticle() throws ClassNotFoundException, SQLException {
		/*----- CrÃ©ation de la connexion Ã  la base de donnÃ©es -----*/
		ConnectionMySql.connexion();

		/*----- Interrogation de la base -----*/
		ArrayList<Article> liste = new ArrayList<>();

		/*----- RequÃªte SQL -----*/
		String sql = "SELECT a.*, c.nomCategorie " + "FROM Articles a "
				+ "INNER JOIN Categories c ON a.IdCategorie = c.IdCategorie";

		/*----- Ouverture de l'espace de requÃªte -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			/*----- ExÃ©cution de la requÃªte -----*/
			try (ResultSet rs = st.executeQuery()) {
				/*----- Lecture du contenu du ResultSet -----*/
				while (rs.next()) {
					Article article = new Article(rs.getInt("EAN"), rs.getString("VignetteArticle"),
							rs.getFloat("PrixUnitaireArticle"), rs.getString("NutriscoreArticle"),
							rs.getString("LibelleArticle"), rs.getFloat("PoidsArticle"), rs.getFloat("PrixKgArticle"),
							rs.getString("DescriptionCourteArticle"), rs.getString("DescriptionLongueArticle"),
							rs.getString("FournisseurArticle"), rs.getString("Marque"), rs.getInt("PromoArticle"),
							rs.getInt("IdRayon"), rs.getInt("IdCategorie"), rs.getInt("IdTypeProduit"));
					article.setNomCategorie(rs.getString("nomCategorie"));
					liste.add(article);
				}
			}
			st.close();
		} catch (SQLException ex) {

			throw new SQLException("Exception ConnectionMySql.afficherArticle() : ProblÃ¨me SQL - " + ex.getMessage());
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
						"Exception ConnectionMySql.getArticleById() : ProblÃ¨me SQL - " + ex.getMessage());
			}

			st.close();
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getArticleById() : ProblÃ¨me SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();
		return article;
	}

	/**
	 * Gets articles from the search box
	 */
	public static ArrayList<Article> chercher(String motSaisi) throws ClassNotFoundException, SQLException {
		/*----- CrÃ©ation de la connexion Ã  la base de donnÃ©es -----*/
		ConnectionMySql.connexion();

		/*----- Interrogation de la base -----*/
		ArrayList<Article> liste = new ArrayList<>();

		/*----- RequÃªte SQL -----*/
		String sql = "SELECT * FROM Articles WHERE Marque LIKE ? OR LibelleArticle LIKE ?";

		/*----- Ouverture de l'espace de requÃªte -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {

			/*----- ExÃ©cution de la requÃªte -----*/
			// Trouver tous les mots qui contiennent la sÃ©quence de caractÃ¨res de motsaisi
			st.setString(1, "%" + motSaisi + "%");
			st.setString(2, "%" + motSaisi + "%");

			try (ResultSet rs = st.executeQuery()) {
				/*----- Lecture du contenu du ResultSet -----*/
				liste = resToArticles(rs);
			}
			st.close();
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.chercher() : ProblÃ¨me SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();

		return liste;
	}

	/**
	 * Upload articles to DB
	 */
	public static void insererArticle(Article article) throws Exception {

		// Crï¿½er la connexion ï¿½ la base de donnï¿½es si elle n'est pas dï¿½jï¿½
		// ï¿½tablie
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

		/*----- CrÃ©ation de la connexion Ã  la base de donnÃ©es -----*/
		ConnectionMySql.connexion();

		/*----- RequÃªte SQL -----*/
		String sql = "SELECT * FROM Commandes WHERE EtatCommande LIKE ?";

		/*----- Ouverture de l'espace de requÃªte -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {

			/*----- ExÃ©cution de la requÃªte -----*/
			// Trouver tous les mots qui contiennent la sÃ©quence de caractÃ¨res de motsaisi
			st.setString(1, commandeEtat);

			try (ResultSet rs = st.executeQuery()) {
				/*----- Lecture du contenu du ResultSet -----*/
				liste = resToCommandes(rs);
			}

		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.panierCommande() : ProblÃ¨me SQL - " + ex.getMessage());
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

		/*----- CrÃ©ation de la connexion Ã  la base de donnÃ©es -----*/
		ConnectionMySql.connexion();

		/*----- RequÃªte SQL -----*/
		String sql = "SELECT Articles.*,qteCom " + "FROM Articles,Articles_Commandes "
				+ "WHERE Articles_Commandes.EAN=Articles.EAN " + "AND IdCommande=?";

		/*----- Ouverture de l'espace de requÃªte -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {

			/*----- ExÃ©cution de la requÃªte -----*/
			// Trouver tous les mots qui contiennent la sÃ©quence de caractÃ¨res de motsaisi
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
			throw new SQLException("Exception ConnectionMySql.DetailCommande() : ProblÃ¨me SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();
		return liste;

	}

	/**
	 * Gets all magasins info
	 */
	public static ArrayList<Magasin> getAllMagasins() throws ClassNotFoundException, SQLException {

		ConnectionMySql.connexion();

		ArrayList<Magasin> listeMag = new ArrayList<>();

		String sql = "SELECT * from Magasins";

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {

			try (ResultSet rs = st.executeQuery()) {

				while (rs.next()) {
					String adresseMagasin = rs.getString("AdresseMagasin");
					String heureFermetureMagasin = rs.getTime("HeureFermetureMagasin").toString();
					String heureOuvertureMagasin = rs.getTime("HeureOuvertureMagasin").toString();
					int idMagasin = rs.getInt("IdMagasin");
					String nomMagasin = rs.getString("NomMagasin");

					Magasin mag = new Magasin(idMagasin, nomMagasin, adresseMagasin, heureOuvertureMagasin,
							heureFermetureMagasin);
					listeMag.add(mag);
				}
			}
			st.close();
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getAllMagasins() : Problï¿½me SQL - " + ex.getMessage());
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
						"Exception ConnectionMySql.getOpeningByMagasinName() : ProblÃ¨me SQL - " + ex.getMessage());
			}

			st.close();
		} catch (SQLException ex) {
			throw new SQLException(
					"Exception ConnectionMySql.getOpeningByMagasinName() : ProblÃ¨me SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();

		return horaire;
	}

	/**
	 * Gets magasin by Id
	 */
	public static Magasin getMagasinById(int idMag) throws ClassNotFoundException, SQLException {
		// Crï¿½er la connexion ï¿½ la base de donnï¿½es
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
						"Exception ConnectionMySql.getMagasinById() : ProblÃ¨me SQL - " + ex.getMessage());
			}

			st.close();
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getMagasinById() : ProblÃ¨me SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();
		return magasin;
	}

	/**
	 * Gets magasin by Id
	 */
	public static Magasin getMagasinByName(String idMag, boolean isToAddCommande)
			throws ClassNotFoundException, SQLException {

		if (!isToAddCommande) {
			ConnectionMySql.connexion();
		}

		Magasin magasin = new Magasin();
		String sql = "SELECT * from Magasins Where NomMagasin = ?";

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			st.setString(1, idMag);
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
						"Exception ConnectionMySql.getMagasinByName() : ProblÃ¨me SQL - " + ex.getMessage());
			}

			st.close();
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getMagasinByName() : ProblÃ¨me SQL - " + ex.getMessage());
		}

		if (!isToAddCommande) {
			ConnectionMySql.cx.close();
		}

		return magasin;
	}

	/**
	 * Gets creneau Id by passing the corresponding times
	 */
	public static int getCreneauIdByTime(Time hDeb, Time hFin, boolean isAddCommande)
			throws ClassNotFoundException, SQLException {

		if (!isAddCommande) {
			ConnectionMySql.connexion();
		}

		int idCreneau = 0;
		String sql = "SELECT IdCreneau from CreneauRetrait Where DebutCreneau = ? AND FinCreneau = ?";

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			st.setTime(1, hDeb);
			st.setTime(2, hFin);
			try (ResultSet rs = st.executeQuery()) {
				// Check if there is at least one row in the ResultSet
				if (rs.next()) {
					idCreneau = rs.getInt("IdCreneau");
					System.out.println(idCreneau);
				} else {
					// Handle the case where no rows were found
					// For example, you can throw an exception or return null
				}
				st.close();
			} catch (SQLException ex) {
				throw new SQLException(
						"Exception ConnectionMySql.getCreneauIdByTime() : ProblÃ¨me SQL - " + ex.getMessage());
			}

			st.close();
		} catch (SQLException ex) {
			throw new SQLException(
					"Exception ConnectionMySql.getCreneauIdByTime() : ProblÃ¨me SQL - " + ex.getMessage());
		}
		if (!isAddCommande) {
			ConnectionMySql.cx.close();
		}

		return idCreneau;
	}

	/**
	 * Gets all the hours opened by magasin Id
	 */
	public static ArrayList<String> getHoursOpenedByMagasinId(String magasinId) {
		Magasin mag = new Magasin();

		try {
			mag = getMagasinByName(magasinId, false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
					heuresCreneaux.add(iO <= 9 ? "0" + Integer.toString(iO) + ":00 - 0" + Integer.toString(iO) + ":30"
							: Integer.toString(iO) + ":00 - " + Integer.toString(iO) + ":30");

					heuresCreneaux
							.add(iO <= 9 ? "0" + Integer.toString(iO) + ":30 - 0" + Integer.toString(iO + 1) + ":00"
									: Integer.toString(iO) + ":30 - " + Integer.toString(iO + 1) + ":00");

				} else if (Integer.parseInt(minuteO) == 30) {
					heuresCreneaux
							.add(iO <= 9 ? "0" + Integer.toString(iO) + ":30 - 0" + Integer.toString(iO + 1) + ":00"
									: Integer.toString(iO) + ":30 - " + Integer.toString(iO + 1) + ":00");

				}
				passOneTour = true;
			} else {
				heuresCreneaux.add(iO <= 9 ? "0" + Integer.toString(iO) + ":00 - 0" + Integer.toString(iO) + ":30"
						: Integer.toString(iO) + ":00 - " + Integer.toString(iO) + ":30");
				heuresCreneaux.add(iO <= 9 ? "0" + Integer.toString(iO) + ":30 - 0" + Integer.toString(iO + 1) + ":00"
						: Integer.toString(iO) + ":30 - " + Integer.toString(iO + 1) + ":00");
			}
		}

		if (Integer.parseInt(minuteF) == 30) {
			heuresCreneaux.add(hF.substring(0, hF.length() - 3) + ":00 - " + hF.substring(0, hF.length() - 3) + ":30");
		}

		return heuresCreneaux;
	}

	/**
	 * Adds order
	 * 
	 * @throws Exception
	 */
	public static void addCommande(String nomMag, Date dateRetrait, Time heureRetraitDeb, Time heureRetraitFin,
			List<Article> articles) throws Exception {
		// Crï¿½er la connexion ï¿½ la base de donnï¿½es
		ConnectionMySql.connexion();

		int auto_incrementId = 0;
		int idCreneau = getCreneauIdByTime(heureRetraitDeb, heureRetraitFin, true);

		String sqlCommande = "INSERT INTO Commandes (EtatCommande, DateRetrait, IdCreneau, IdMagasin, IdUtilisateur) VALUES (?, ?, ?, ?, ?)";

		// Get Magasin by Name
		Magasin magasin = getMagasinByName(nomMag, true);

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sqlCommande, Statement.RETURN_GENERATED_KEYS)) {

			st.setString(1, "En cours");
			st.setDate(2, dateRetrait);
			st.setInt(3, idCreneau);
			st.setInt(4, magasin.getIdMagasin());
			st.setInt(5, 1);

			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				auto_incrementId = rs.getInt(1);
			}
			st.close();
		} catch (SQLException sqle) {
			throw new Exception("Erreur lors de l'insertion de la commande : " + sqle.getMessage());
		}

		for (Article art : articles) {
			String sqlArticleCommande = "INSERT INTO Articles_Commandes (EAN, IdCommande, qteCom) VALUES (?, ?, ?)";
			try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sqlArticleCommande)) {

				st.setInt(1, art.getEAN());
				st.setInt(2, auto_incrementId);
				st.setInt(3, art.getQuantite());

				st.executeUpdate();
				st.close();

			} catch (SQLException e) {
				throw new Exception("Bd.addCommande() - " + e.getMessage());
			}
		}

		ConnectionMySql.cx.close();
	}

	public static HashMap<String, List<Object[]>> getContenuListe(int idListe)
			throws ClassNotFoundException, SQLException {

		HashMap<String, List<Object[]>> contenuListeMap = new HashMap<>();

		try {
			ConnectionMySql.connexion();

			/* Requête SQL */
			String sql = "SELECT l.NomListe, t.NomTypeProduit, a.LibelleArticle, c.quantite "
					+ "FROM Contenu_Liste c, Liste_Courses l, TypeProduit t, Articles a "
					+ "WHERE t.IdTypeProduit = c.IdTypeProduit AND c.IdListe = l.IdListe AND c.EAN = a.EAN AND c.IdListe = ?";

			try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
				st.setInt(1, idListe);
				try (ResultSet rs = st.executeQuery()) {
					while (rs.next()) {
						String nomListe = rs.getString("NomListe");
						String NomTypeProduit = rs.getString("NomTypeProduit");
						String libelleArticle = rs.getString("LibelleArticle");
						int quantite = rs.getInt("quantite");

						Object[] contenuListe = { NomTypeProduit, libelleArticle, quantite };

						contenuListeMap.computeIfAbsent(nomListe, k -> new ArrayList<>()).add(contenuListe);
					}
				}
			} catch (SQLException ex) {
				throw new SQLException(
						"Exception ConnectionMySql.getContenuListe() : Problème SQL - " + ex.getMessage());
			}
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getContenuListe() : Problème SQL - " + ex.getMessage());
		}
		return contenuListeMap;
	}

	public static void insererLigneListe(int idListe, int idTypeProduit, int EAN, int quantite)
			throws SQLException, ClassNotFoundException {
		ConnectionMySql.connexion();
		String insertQuery = "INSERT INTO Contenu_Liste(EAN, IdTypeProduit, quantite, IdListe) VALUES (?,?,?,?)";

		try (PreparedStatement insertStmt = ConnectionMySql.cx.prepareStatement(insertQuery)) {
			// Remplacer les paramètres de requête par les nouvelles valeurs
			insertStmt.setInt(1, EAN);
			insertStmt.setInt(2, idTypeProduit);
			insertStmt.setInt(3, quantite);
			insertStmt.setInt(4, idListe);

			// Exécuter la mise à jour
			insertStmt.executeUpdate();
		}

		ConnectionMySql.cx.close();

	}

	public static void supprimerListeCourse(String nomListe) throws SQLException, ClassNotFoundException {
		ConnectionMySql.connexion();

		// Requête SQL pour la suppression
		String deleteQuery = "DELETE FROM Liste_Courses WHERE NomListe = ?";

		try (PreparedStatement deleteStmt = ConnectionMySql.cx.prepareStatement(deleteQuery)) {
			// Remplacer le paramètre de requête par le nom de la liste à supprimer
			deleteStmt.setString(1, nomListe);

			// Exécuter la suppression
			deleteStmt.executeUpdate();
		}

		ConnectionMySql.cx.close();
	}

	public static int addListeCourse(String nomLC, List<String> listIdTypeProduit) throws Exception {
		ConnectionMySql.connexion();

		int idListe = 0;

		String insertQuery = "INSERT INTO Liste_Courses(IdUtilisateur, NomListe) VALUES (?, ?)";

		try (PreparedStatement insertStmt = ConnectionMySql.cx.prepareStatement(insertQuery,
				Statement.RETURN_GENERATED_KEYS)) {

			insertStmt.setInt(1, 1);
			insertStmt.setString(2, nomLC);

			// Exécuter l'insertion
			insertStmt.executeUpdate();

			ResultSet generatedKeys = insertStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				idListe = generatedKeys.getInt(1);
				System.out.println("Liste de courses ajoutée avec l'identifiant : " + idListe + " " + nomLC);
			} else {
				throw new SQLException("Échec de la récupération de l'identifiant de la liste de courses ajoutée.");
			}
		}

		if (listIdTypeProduit != null) {
			for (String idTypeProduit : listIdTypeProduit) {
				String sqltypeProduitListeCourse = "INSERT INTO Contenu_Liste (IdListe, IdTypeProduit) VALUES (?, ?)";
				try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sqltypeProduitListeCourse)) {

					st.setInt(1, idListe);
					st.setString(2, idTypeProduit);

					st.executeUpdate();
					st.close();

				} catch (SQLException e) {
					throw new Exception("Bd.addListeCourse() - " + e.getMessage());
				}
			}
		}

		ConnectionMySql.cx.close();

		return idListe;
	}

	public static ArrayList<Commande> getAllCommande() throws ClassNotFoundException, SQLException {
		ArrayList<Commande> liste = new ArrayList<>();
		connexion();

		String sql = "SELECT * FROM Commandes c, CreneauRetrait cr, Magasins m, Articles_Commandes ac, Articles a WHERE c.IdCreneau = cr.IdCreneau AND a.EAN=ac.EAN AND c.IdCommande = ac.IdCommande AND m.IdMagasin = c.IdMagasin AND IdUtilisateur = 1 AND EtatCommande = 'En cours' GROUP BY(c.IdCommande)";
		try (PreparedStatement st = cx.prepareStatement(sql)) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Commande commande = new Commande(rs.getDate("DateRetrait"), rs.getString("EtatCommande"),
							rs.getInt("IdCommande"), rs.getInt("IdMagasin"), rs.getInt("IdUtilisateur"));
					commande.setDebutCreneau(rs.getTime("DebutCreneau"));
					commande.setFinCreneau(rs.getTime("FinCreneau"));
					commande.setNomMagasin(rs.getString("NomMagasin"));
					;
					liste.add(commande);
				}
			}
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getAllCommande() : Problème SQL - " + ex.getMessage());
		} finally {
			cx.close();
		}
		return liste;
	}

	public static Commande getCommandeById(int idCommande) throws ClassNotFoundException, SQLException {
		connexion(); // Créez la connexion à la base de données

		Commande commande = null;

		String sql = "SELECT c.*, cr.DebutCreneau, cr.FinCreneau, m.NomMagasin FROM Commandes c "
				+ "JOIN CreneauRetrait cr ON c.IdCreneau = cr.IdCreneau "
				+ "JOIN Magasins m ON c.IdMagasin = m.IdMagasin " + "WHERE c.IdCommande = ?";
		try (PreparedStatement st = cx.prepareStatement(sql)) {
			st.setInt(1, idCommande);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					commande = new Commande(rs.getDate("DateRetrait"), rs.getString("EtatCommande"),
							rs.getInt("IdCommande"), rs.getInt("IdUtilisateur"), rs.getInt("IdMagasin"));
					commande.setDebutCreneau(rs.getTime("DebutCreneau"));
					commande.setFinCreneau(rs.getTime("FinCreneau"));
					commande.setNomMagasin(rs.getString("NomMagasin"));
				}
			}
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getCommandeById() : Problème SQL - " + ex.getMessage());
		} finally {
			cx.close(); // Fermez la connexion à la base de données
		}
		return commande;
	}

	public static void updateCommande(int idCommande, int idCreneau, int idMagasin)
			throws ClassNotFoundException, SQLException {
		connexion();

		String sql = "UPDATE Commandes SET IdCreneau = ?, IdMagasin = ? WHERE IdCommande = ?";
		try (PreparedStatement st = cx.prepareStatement(sql)) {
			st.setInt(1, idCreneau);
			st.setInt(2, idMagasin);
			st.setInt(3, idCommande);

			st.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.updateCommande() : Problème SQL - " + ex.getMessage());
		} finally {
			cx.close();
		}
	}

	public static ArrayList<CreneauRetrait> getHoursOpenedByMagasin(String nomMagasin)
			throws ClassNotFoundException, SQLException {
		connexion();

		String sql = "SELECT * FROM CreneauRetrait c, Magasins m, Magasins_CreneauRetraits mag WHERE c.IdCreneau = mag.IdCreneau AND m.IdMagasin = mag.Idmagasin AND nomMagasin = ?";
		ArrayList<CreneauRetrait> creneaux = new ArrayList<>();
		try (PreparedStatement st = cx.prepareStatement(sql)) {
			st.setString(1, nomMagasin);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					CreneauRetrait creneau = new CreneauRetrait();
					creneau.setIdCreneau(rs.getInt("idCreneau"));
					creneau.setDebutCreneau(rs.getString("debutCreneau"));
					creneau.setFinCreneau(rs.getString("finCreneau"));
					creneaux.add(creneau);
				}
			}
		} catch (SQLException ex) {
			throw new SQLException(
					"Exception ConnectionMySql.getHoursOpenedByMagasinId() : Problème SQL - " + ex.getMessage());
		} finally {
			cx.close();
		}
		return creneaux;
	}

	public static HashMap<Commande, ArrayList<Article>> getAllCommandeWithArticles()
			throws ClassNotFoundException, SQLException {
		HashMap<Commande, ArrayList<Article>> commandesArticlesMap = new HashMap<>();
		connexion();

		String sql = "SELECT * FROM Commandes c " + "JOIN CreneauRetrait cr ON c.IdCreneau = cr.IdCreneau "
				+ "JOIN Magasins m ON m.IdMagasin = c.IdMagasin "
				+ "JOIN Articles_Commandes ac ON c.IdCommande = ac.IdCommande " + "JOIN Articles a ON a.EAN = ac.EAN "
				+ "WHERE IdUtilisateur = 1 AND EtatCommande = 'En cours'";

		try (PreparedStatement st = cx.prepareStatement(sql)) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					// Création de la commande
					Commande commande = new Commande(rs.getDate("DateRetrait"), rs.getString("EtatCommande"),
							rs.getInt("IdCommande"), rs.getInt("IdMagasin"), rs.getInt("IdUtilisateur"));
					commande.setDebutCreneau(rs.getTime("DebutCreneau"));
					commande.setFinCreneau(rs.getTime("FinCreneau"));
					commande.setNomMagasin(rs.getString("NomMagasin"));

					// Création de l'article
					Article article = new Article(rs.getInt("EAN"), rs.getString("VignetteArticle"),
							rs.getFloat("PrixUnitaireArticle"), rs.getString("NutriscoreArticle"),
							rs.getString("LibelleArticle"), rs.getFloat("PoidsArticle"), rs.getFloat("PrixKgArticle"),
							rs.getString("DescriptionCourteArticle"), rs.getString("DescriptionLongueArticle"),
							rs.getString("FournisseurArticle"), rs.getString("Marque"), rs.getInt("PromoArticle"),
							rs.getInt("IdRayon"), rs.getInt("IdCategorie"), rs.getInt("IdTypeProduit"));

					// Vérifier si la commande est déjà dans la map
					if (commandesArticlesMap.containsKey(commande)) {
						// Si oui, récupérer la liste d'articles associée
						ArrayList<Article> articles = commandesArticlesMap.get(commande);
						// Ajouter l'article à la liste
						articles.add(article);
					} else {
						// Si non, créer une nouvelle liste d'articles
						ArrayList<Article> articles = new ArrayList<>();
						// Ajouter l'article à la liste
						articles.add(article);
						// Ajouter la commande et sa liste d'articles à la map
						commandesArticlesMap.put(commande, articles);
					}
				}
			}
		} catch (SQLException ex) {
			throw new SQLException(
					"Exception ConnectionMySql.getAllCommandeWithArticles() : Problème SQL - " + ex.getMessage());
		} finally {
			cx.close();
		}

		return commandesArticlesMap;
	}

	/**
	 * Retourne la liste de type de produits.
	 */
	public static ArrayList<TypeProduit> afficherTypeProduit() throws ClassNotFoundException, SQLException {
		/*----- Création de la connexion à la base de données -----*/
		ConnectionMySql.connexion();

		/*----- Interrogation de la base -----*/
		ArrayList<TypeProduit> liste = new ArrayList<>();

		/*----- Requête SQL -----*/
		String sql = "SELECT * from TypeProduit";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			/*----- Exécution de la requête -----*/
			try (ResultSet rs = st.executeQuery()) {
				/*----- Lecture du contenu du ResultSet -----*/
				while (rs.next()) {
					liste.add(new TypeProduit(rs.getInt("IdTypeProduit"), rs.getString("NomTypeProduit"),
							rs.getInt("IdCategorie")));
				}
			}
			st.close();
		} catch (SQLException ex) {

			throw new SQLException(
					"Exception ConnectionMySql.afficherTypeProduit() : Problème SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();
		return liste;
	}

	/**
	 * Gets type product Id by TP name
	 */
	public static int getCategoryIdByTPName(String nomTP, boolean isFilter)
			throws ClassNotFoundException, SQLException {
		/*----- Création de la connexion à la base de données -----*/
		if (!isFilter) {
			ConnectionMySql.connexion();
		}

		/*----- Interrogation de la base -----*/
		int idTP = 0;

		/*----- Requête SQL -----*/
		String sql = "SELECT IdCategorie FROM Categories WHERE NomCategory = ?";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			// Définir la valeur du paramètre idCat dans la requête SQL
			st.setString(1, nomTP);
			/*----- Exécution de la requête -----*/
			try (ResultSet rs = st.executeQuery()) {
				/*----- Lecture du contenu du ResultSet -----*/
				if (rs.next()) {
					idTP = rs.getInt("IdCategorie");
				}
			}
			st.close();
		} catch (SQLException ex) {

			throw new SQLException(
					"Exception ConnectionMySql.getCategoryIdByTPName() : Problème SQL - " + ex.getMessage());
		}
		if (!isFilter) {
			ConnectionMySql.cx.close();
		}
		return idTP;
	}

	/**
	 * Gets articles by TP name
	 */
	public static ArrayList<Article> getArticlesByTPName(String nomTP) throws ClassNotFoundException, SQLException {
		/*----- Création de la connexion à la base de données -----*/
		ConnectionMySql.connexion();

		int idTP = getCategoryIdByTPName(nomTP, true);
		ArrayList<Article> liste = new ArrayList<>();

		/*----- Requête SQL -----*/
		String sql = "SELECT * FROM Articles WHERE IdCategorie = ?";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			// Définir la valeur du paramètre idCat dans la requête SQL
			st.setString(1, Integer.toString(idTP));
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

			throw new SQLException(
					"Exception ConnectionMySql.getArticlesByTPName() : Problème SQL - " + ex.getMessage());
		}
		ConnectionMySql.cx.close();
		return liste;
	}

	public static ArrayList<ListeCourse> getListesCourses() throws ClassNotFoundException, SQLException {
		ArrayList<ListeCourse> courses = new ArrayList<>();

		try {
			ConnectionMySql.connexion();

			/* Requête SQL */
			String sql = "SELECT * FROM Liste_Courses";

			try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
				try (ResultSet rs = st.executeQuery()) {
					while (rs.next()) {
						String nomListe = rs.getString("NomListe");
						int idListe = rs.getInt("IdListe");
						int idUtilisateur = rs.getInt("IdUtilisateur");

						courses.add(new ListeCourse(idListe, nomListe, idUtilisateur));
					}
				}
			} catch (SQLException ex) {
				throw new SQLException(
						"Exception ConnectionMySql.getListesCourses() : Problème SQL - " + ex.getMessage());
			}

		} catch (SQLException ex) {
			throw new SQLException("Exception ConnectionMySql.getListesCourses() : Problème SQL - " + ex.getMessage());
		}

		return courses;
	}

	public static ArrayList<Article> getAllArticlesByTypeProduit(int idTypeProduit)
			throws ClassNotFoundException, SQLException {
		ConnectionMySql.connexion();

		ArrayList<Article> liste = new ArrayList<>();

		String sql = "SELECT DISTINCT * FROM Articles a, TypeProduit t, Contenu_Liste c WHERE t.IdTypeProduit = a.IdTypeProduit AND c.IdTypeProduit = a.IdTypeProduit AND a.IdTypeProduit = ? GROUP BY a.EAN";

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {

			st.setInt(1, idTypeProduit);

			try (ResultSet rs = st.executeQuery()) {

				while (rs.next()) {
					Article article = new Article(rs.getInt("EAN"), rs.getString("VignetteArticle"),
							rs.getFloat("PrixUnitaireArticle"), rs.getString("NutriscoreArticle"),
							rs.getString("LibelleArticle"), rs.getFloat("PoidsArticle"), rs.getFloat("PrixKgArticle"),
							rs.getString("DescriptionCourteArticle"), rs.getString("DescriptionLongueArticle"),
							rs.getString("FournisseurArticle"), rs.getString("Marque"), rs.getInt("PromoArticle"),
							rs.getInt("IdRayon"), rs.getInt("IdCategorie"), rs.getInt("IdTypeProduit"));
					article.setNomTypeProduit(rs.getString("NomTypeProduit"));
					liste.add(article);
				}
			}
		} catch (SQLException ex) {
			throw new SQLException(
					"Exception ConnectionMySql.getAllArticlesByTypeProduit() : Problème SQL - " + ex.getMessage());
		}

		// Fermeture de la connexion
		ConnectionMySql.cx.close();

		return liste;
	}

	public static ArrayList<ContenuListe> getContenuListeByIdForTypeProduit(int idListe)
			throws SQLException, ClassNotFoundException {
		ArrayList<ContenuListe> liste = new ArrayList<>();

		// Establish the database connection
		ConnectionMySql.connexion();

		String sql = "SELECT c.*, t.NomTypeProduit " + "FROM Contenu_Liste c "
				+ "JOIN TypeProduit t ON c.IdTypeProduit = t.IdTypeProduit "
				+ "WHERE c.EAN IS NULL AND c.quantite IS NULL AND c.idListe = ?";

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			st.setInt(1, idListe);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					ContenuListe contenu = new ContenuListe(rs.getInt("IdListe"), rs.getInt("EAN"),
							rs.getInt("IdTypeProduit"), rs.getInt("quantite")

					);
					contenu.setNomTypeProduit(rs.getString("NomTypeProduit"));
					liste.add(contenu);
				}
			}
		} catch (SQLException ex) {
			throw new SQLException("Exception in getContenuListeByIdForTypeProduit: " + ex.getMessage());
		} finally {
			ConnectionMySql.cx.close();
		}

		return liste;
	}

	public static ArrayList<ContenuListe> getContenuListeByIdForArticle(int idListe)
			throws SQLException, ClassNotFoundException {
		ArrayList<ContenuListe> liste = new ArrayList<>();

		// Establish the database connection
		ConnectionMySql.connexion();

		String sql = "SELECT c.*, t.NomTypeProduit, a.LibelleArticle, a.Marque " + "FROM Contenu_Liste c "
				+ "JOIN TypeProduit t ON c.IdTypeProduit = t.IdTypeProduit " + "JOIN Articles a ON c.EAN = a.EAN "
				+ "WHERE c.EAN IS NOT NULL AND c.quantite IS NOT NULL AND c.idListe = ?";

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			st.setInt(1, idListe);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					ContenuListe contenu = new ContenuListe(rs.getInt("IdListe"), rs.getInt("EAN"),
							rs.getInt("IdTypeProduit"), rs.getInt("quantite"));
					contenu.setLibelleArticle(rs.getString("LibelleArticle"));
					contenu.setMarque(rs.getString("Marque"));
					liste.add(contenu);
				}
			}
		} catch (SQLException ex) {
			throw new SQLException("Exception in getContenuListeByIdForArticle: " + ex.getMessage());
		} finally {
			ConnectionMySql.cx.close();
		}

		return liste;
	}

	public static void updateContenuListe(int newEAN, int newQuantite, int idListe, int idTypeProduit)
			throws ClassNotFoundException, SQLException {
		connexion(); // Ouvre la connexion à la base de données

		// Requête SQL d'update
		String sql = "UPDATE Contenu_Liste " + "SET EAN = ?, quantite = ? "
				+ "WHERE idListe = ? AND idTypeProduit = ? AND EAN IS NULL AND quantite IS NULL";

		try (PreparedStatement st = cx.prepareStatement(sql)) {
			// Assignation des valeurs aux paramètres de la requête
			st.setInt(1, newEAN);
			st.setInt(2, newQuantite);
			st.setInt(3, idListe);
			st.setInt(4, idTypeProduit);

			// Exécution de la requête
			st.executeUpdate();
		} catch (SQLException sqle) {
			throw new SQLException("Erreur lors de la mise à jour du contenu de la liste : " + sqle.getMessage());
		} finally {
			// Fermeture de la connexion à la base de données
			if (cx != null) {
				cx.close();
			}
		}
	}

	/**
	 * Delete all information of liste course by Id
	 */
	public static void deleteListeCourseById(String idListeCourse) throws ClassNotFoundException, SQLException {
		connexion(); // Ouvre la connexion à la base de données

		// Requête SQL d'update
		String sqlDelteContenu = "DELETE FROM Contenu_Liste WHERE IdListe = ?";
		String sqlDelteListe = "DELETE FROM Liste_Courses WHERE IdListe = ?";

		try (PreparedStatement st = cx.prepareStatement(sqlDelteContenu)) {
			// Assignation des valeurs aux paramètres de la requête
			st.setInt(1, Integer.parseInt(idListeCourse));

			// Exécution de la requête
			st.executeUpdate();
			st.close();
		} catch (SQLException sqle) {
			throw new SQLException("Erreur lors de la mise à jour du contenu de la liste : " + sqle.getMessage());
		}

		try (PreparedStatement st = cx.prepareStatement(sqlDelteListe)) {
			// Assignation des valeurs aux paramètres de la requête
			st.setString(1, idListeCourse);

			// Exécution de la requête
			st.executeUpdate();
			st.close();
		} catch (SQLException sqle) {
			throw new SQLException("Erreur lors de la mise à jour du contenu de la liste : " + sqle.getMessage());
		}

		// Fermeture de la connexion à la base de données
		if (cx != null) {
			cx.close();
		}
	}

	/**
	 * Get all the articles to add in card from liste course by course id
	 */
	public static ArrayList<Article> getArticlesInfoByListeCourseId(int idListe)
			throws SQLException, ClassNotFoundException {
		ArrayList<Article> liste = new ArrayList<>();
		Article article = new Article();

		// Establish the database connection
		ConnectionMySql.connexion();

		String sql = "SELECT Articles.*, Contenu_Liste.quantite FROM Articles, Contenu_Liste WHERE Articles.EAN = Contenu_Liste.EAN And Contenu_Liste.IdListe = ?";

		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
			st.setInt(1, idListe);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					article = new Article(rs.getInt("EAN"), rs.getString("VignetteArticle"),
							rs.getFloat("PrixUnitaireArticle"), rs.getString("NutriscoreArticle"),
							rs.getString("LibelleArticle"), rs.getFloat("PoidsArticle"), rs.getFloat("PrixKgArticle"),
							rs.getString("DescriptionCourteArticle"), rs.getString("DescriptionLongueArticle"),
							rs.getString("FournisseurArticle"), rs.getString("Marque"), rs.getInt("PromoArticle"),
							rs.getInt("IdRayon"), rs.getInt("IdCategorie"), rs.getInt("IdTypeProduit"));
					article.setQuantite(rs.getInt("quantite"));
					liste.add(article);
				}
			}
			st.close();
		} catch (SQLException ex) {
			throw new SQLException("Exception in getArticlesInfoByListeCourseId: " + ex.getMessage());
		} finally {
			ConnectionMySql.cx.close();
		}

		return liste;
	}

	/*----------------------------*/
	/* Programme principal (test) */
	/*----------------------------*/

	public static void main(String[] s) throws Exception {
//		getHoursOpenedByMagasinId("MeubleLand");
//		getOpeningByMagasinName("ElectroPlus");

//		System.out.println(getContenuListe(1));
//		insererLigneListe(1, 18, 23, 4);

//		System.out.println(getContenuListe(1));
		System.out.println(getAllArticlesByTypeProduit(1));

	}
} /*----- Fin de la classe ConnectionMySql -----*/
