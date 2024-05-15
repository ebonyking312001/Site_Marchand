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
	public static ArrayList<String> afficherArticle () throws ClassNotFoundException, SQLException
		{
		/*----- Création de la connexion à la base de données -----*/
		if (ConnectionMySql.cx == null)
			ConnectionMySql.connexion();

		/*----- Interrogation de la base -----*/
		ArrayList<String> liste = new ArrayList<>();

		/*----- Requête SQL -----*/
		String sql = "SELECT * from Articles";

		/*----- Ouverture de l'espace de requête -----*/
		try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql))
			{
			/*----- Exécution de la requête -----*/
			try (ResultSet rs = st.executeQuery())
				{
				/*----- Lecture du contenu du ResultSet -----*/
				while (rs.next())
					liste.add(rs.getString(2));
				}
			}
		catch (SQLException ex)
			{
			throw new SQLException("Exception ConnectionMySql.lireCitations() : Problème SQL - " + ex.getMessage());
			}

		return liste;
		}
	
	
	public static ArrayList<Article> afficherArticleCatalogue() throws ClassNotFoundException, SQLException {
	    // Cr�er la connexion � la base de donn�es
	    if (ConnectionMySql.cx == null)
	        ConnectionMySql.connexion();

	    // Liste pour stocker les articles
	    ArrayList<Article> liste = new ArrayList<>();

	    // Requ�te SQL
	    String sql = "SELECT * from Articles";

	    // Ouverture de l'espace de requ�te
	    try (PreparedStatement st = ConnectionMySql.cx.prepareStatement(sql)) {
	        // Ex�cution de la requ�te
	        try (ResultSet rs = st.executeQuery()) {
	            // Lecture du contenu du ResultSet
	            while (rs.next()) {
	                double EAN = rs.getDouble("EAN");
	                String vignetteArticle = rs.getString("VignetteArticle");
	                double prixUnitaireArticle = rs.getDouble("PrixUnitaireArticle");
	                String NutriscoreArticle = rs.getString("NutriscoreArticle");
	                String libelleArticle = rs.getString("LibelleArticle");
	                double poidsArticle = rs.getDouble("PoidsArticle");
	                double prixKgArticle = rs.getDouble("PrixKgArticle");
	                String descriptionCourteArticle = rs.getString("DescriptionCourteArticle");
	                String descriptionLongueArticle = rs.getString("DescriptionLongueArticle");
	                String fournisseurArticle = rs.getString("FournisseurArticle");
	                String marque = rs.getString("Marque");
	                int idRayon = rs.getInt("IdRayon");

	                // Cr�er un nouvel article et l'ajouter � la liste
	                Article article = new Article(EAN, vignetteArticle, prixUnitaireArticle,
	                                              NutriscoreArticle, libelleArticle, poidsArticle, prixKgArticle,
	                                              descriptionCourteArticle, descriptionLongueArticle, fournisseurArticle,
	                                              marque, idRayon);
	                liste.add(article);
	            }
	        }
	    } catch (SQLException ex) {
	        throw new SQLException("Exception ConnectionMySql.afficherArticleCatalogue() : Probl�me SQL - " + ex.getMessage());
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


	/*----------------------------*/
	/* Programme principal (test) */
	/*----------------------------*/

	public static void main (String[] s) throws Exception
		{
		try {
			ArrayList<Article> l = ConnectionMySql.afficherArticleCatalogue();
			for (Article msg : l) {
				System.out.println(msg);
			}
			}
		catch (ClassNotFoundException | SQLException ex)
			{
			System.out.println(ex.getMessage());
			}
		}

} /*----- Fin de la classe ConnectionMySql -----*/
