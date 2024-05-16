package bd;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Article;



/**
 * Classe en charge de la base de données.
 */
public class ConnectionMySql
{
	/*---------*/
	/* Données */
	/*---------*/

	/*----- Connexion -----*/
	private static Connection cx = null;

	/*----- Données de connexion -----*/
	private static final String URL			= "jdbc:mysql://srv1049.hstgr.io:3306/u523250608_projetdai";
	private static final String LOGIN		= "u523250608_projetdai";
	private static final String PASSWORD	= "Projetdai1$";


	/*----------*/
	/* Méthodes */
	/*----------*/

	/**
	 * Crée la connexion avec la base de données.
	 */
	private static void connexion() throws ClassNotFoundException, SQLException
		{
		/*----- Chargement du pilote pour la ConnectionMySql -----*/
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			}
		catch (ClassNotFoundException ex)
			{
			throw new ClassNotFoundException("Exception ConnectionMySql.connexion() - Pilote MySql introuvable - " + ex.getMessage());
			}

		/*----- Ouverture de la connexion -----*/
		try {
			ConnectionMySql.cx = DriverManager.getConnection(URL,LOGIN,PASSWORD);
			}
		catch (SQLException ex)
			{
			throw new SQLException("Exception ConnectionMySql.connexion() - Problème de connexion à la base de données - " + ex.getMessage());
			}
		}


	/**
	 * Retourne la liste de articles.
	 */
	public static ArrayList<Article> afficherArticle () throws ClassNotFoundException, SQLException
		{
		/*----- Création de la connexion à la base de données -----*/
		if (ConnectionMySql.cx == null)
			ConnectionMySql.connexion();

		/*----- Interrogation de la base -----*/
		ArrayList<Article> liste = new ArrayList<>();

		/*----- Requête SQL -----*/
		String sql = "SELECT * from Articles";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)){
			/*----- Exécution de la requête -----*/
			try (ResultSet rs = st.executeQuery()){
				/*----- Lecture du contenu du ResultSet -----*/
				while (rs.next()) {
	                liste.add(new Article(rs.getInt("EAN"), 
		                                 rs.getString("VignetteArticle"),
		                                 rs.getFloat("PrixUnitaireArticle"),
		                                 rs.getString("NutriscoreArticle"),
		                                 rs.getString("LibelleArticle"),
		                                 rs.getFloat("PoidsArticle"),
		                                 rs.getFloat("PrixKgArticle"),
		                                 rs.getString("DescriptionCourteArticle"),
		                                 rs.getString("DescriptionLongueArticle"),
		                                 rs.getString("FournisseurArticle"),
		                                 rs.getString("Marque"),
		                                 rs.getInt("IdRayon")
		                     )
                     );
	            }
			}
		}
		catch (SQLException ex)
			{
			throw new SQLException("Exception ConnectionMySql.lireCitations() : Problème SQL - " + ex.getMessage());
			}

		return liste;
		}
	

	/**
	 * Retourne le mot cherché existant dans la ConnectionMySqld
	 */
	public static ArrayList<String> chercher (String motSaisi) throws ClassNotFoundException, SQLException {
		/*----- Création de la connexion à la base de données -----*/
		if (ConnectionMySql.cx == null)
			ConnectionMySql.connexion();
		
		 /*----- Interrogation de la base -----*/
	    ArrayList<String> liste = new ArrayList<>();

	    /*----- Requête SQL -----*/
	    String sql = "SELECT Texte FROM Mot WHERE Texte LIKE ?";
	    
	    /*----- Ouverture de l'espace de requête -----*/
	    try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
	    	
	        /*----- Exécution de la requête -----*/
	    	// Trouver tous les mots qui contiennent la séquence de caractères de motsaisi
	        st.setString(1, motSaisi + "%");
	        
	        try (ResultSet rs = st.executeQuery()) {
	            /*----- Lecture du contenu du ResultSet -----*/
	            while (rs.next())
	                liste.add(rs.getString(1));
	        }
	        
	    } catch (SQLException ex) {
	        throw new SQLException("Exception ConnectionMySql.chercher() : Problème SQL - " + ex.getMessage());
	    }
	    return liste;
		
	}
	
	/**
	 * Insérer un texte dans la table mot 
	 * @throws Exception 
	 */
	public static int inserer (String motSaisi) throws Exception {
		/*----- Création de la connexion à la base de données -----*/
		if (ConnectionMySql.cx == null)
			ConnectionMySql.connexion();
		
		/* --- Requête d'insertion --- */
		  int nb = 0;
		  String sql = "INSERT INTO Mot (Texte) VALUES (?)";
		  
		  try (PreparedStatement st = cx.prepareStatement(sql)){
			  // Insertion des paramères
			  st.setString(1, motSaisi);
			  
			  nb = st.executeUpdate();
			  
		  } catch (SQLException sqle) {
			  throw new Exception("ConnectionMySql.inserer() - " + sqle.getMessage()); 
		  }
		  
		  return nb;
		
	}
	
	public static void insererArticle(Article article) throws Exception {
	    // Cr�er la connexion � la base de donn�es si elle n'est pas d�j� �tablie
	    if (ConnectionMySql.cx == null) {
	        ConnectionMySql.connexion();
	    }

	    // Requ�te SQL d'insertion
	    String sql = "INSERT INTO Articles (EAN, VignetteArticle, PrixUnitaireArticle, NutriscoreArticle, LibelleArticle, PoidsArticle, PrixKgArticle, DescriptionCourteArticle, DescriptionLongueArticle, FournisseurArticle, Marque, IdRayon) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement st = cx.prepareStatement(sql)) {
	        // Assigner les valeurs des param�tres de la requ�te
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
	        st.setInt(12, article.getIdRayon());

	        // Ex�cuter la requ�te
	        st.executeUpdate();
	    } catch (SQLException sqle) {
	        throw new Exception("Erreur lors de l'insertion de l'article : " + sqle.getMessage());
	    }
	}


	/*----------------------------*/
	/* Programme principal (test) */
	/*----------------------------*/

	public static void main (String[] s) throws Exception
		{
		try {
			ArrayList<Article> l = ConnectionMySql.afficherArticle();
			for (Article msg : l) {
				System.out.println(msg);
			}
			System.out.println("hallo");
			}
		catch (ClassNotFoundException | SQLException ex)
			{
			System.out.println(ex.getMessage());
			}
		}

} /*----- Fin de la classe ConnectionMySql -----*/
