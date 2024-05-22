package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.ConnectionMySql;
import model.Article;
import model.Categorie;
import model.TypeProduit;

/**
 * Servlet implementation class ServletAjout
 */
@WebServlet("/")
public class CtrlArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Gets session
		HttpSession session = request.getSession();
		// Badge card
		var countArtCard = session.getAttribute("countArtCard");
		if (countArtCard == null) {
			session.setAttribute("countArtCard", 0);
		}

		// get article id
		String idArticle = request.getParameter("idArticle");
		// get category id
		String idCategorie = request.getParameter("idCategorie");
		// get product type id
		String idTypeProd = request.getParameter("idTypeProd");
		// get order type croissant/decroissant
		String ordre = request.getParameter("ordre");
		// get action (annuler)
		String action = request.getParameter("action");
		
		// set sessions
		if (idCategorie != null) {
			
			// Garder id_choisi dans session id_choisi
			session.setAttribute("idCategorie", idCategorie);
			session.removeAttribute("idTypeProd");
		}
		if (idTypeProd != null) {
			
			// Garder id_choisi dans session id_choisi
			session.setAttribute("idTypeProd", idTypeProd);
		}
		if (ordre != null) {
			
			// Garder id_choisi dans session id_choisi
			session.setAttribute("ordre", ordre);
		}
		// effacer session idCategorie, session idTypeProd, session ordre
		if (action != null) {
			
			System.out.println("action="+action);
			session.removeAttribute("idCategorie");
			session.removeAttribute("idTypeProd");
			session.removeAttribute("ordre");
		}
		// Récupérer session Id Categorie
		String sessionIdCategorie = (String) session.getAttribute("idCategorie");
		// Récupérer session Id type prod
		String sessionIdTypeProd = (String) session.getAttribute("idTypeProd");
		// Récupérer session ordre
		String sessionOrdre = (String) session.getAttribute("ordre");
//		System.out.println("sessionIdCategorie="+sessionIdCategorie+", sessionIdTypeProd="+sessionIdTypeProd+", sessionOrdre="+sessionOrdre);

		try {
			// Afficher toutes les catégories
			ArrayList<Categorie> listeCat = ConnectionMySql.afficherCategorie();
			// Renvoyer détails d'un article quand un article est choisi
			if (idArticle != null) {
				
				// Récupérer info article par id article
				Article article = ConnectionMySql.getArticleById(idArticle);

				request.setAttribute("article", article);
				request.getRequestDispatcher("/jsp/Details.jsp").forward(request, response);
				return;
			}

			ArrayList<Article> listeArt = null;
			ArrayList<TypeProduit> listeTypeProd = null;
			// Afficher article par rapport aux filtres catégories, types produits
			if ((sessionIdCategorie != null) && (sessionIdTypeProd == null)) {
				
				listeArt = ConnectionMySql.afficherArticleByCategory(sessionIdCategorie);
				listeTypeProd = ConnectionMySql.afficherProductTypeByCategory(sessionIdCategorie);
				System.out.println("liste d'articles changé : filtre par idCatégorie " + sessionIdCategorie + "/n" + listeArt);
				// Renvoyer id catégorie choisie
				request.setAttribute("idCategorieChoisi", sessionIdCategorie);
				if(sessionOrdre != null) {
					
					request.setAttribute("ordreChoisi", sessionOrdre);	
					listeArt = sortArticleListByOrder(listeArt,sessionOrdre);
				}
			} else if (sessionIdTypeProd != null) {
				
				listeArt = ConnectionMySql.afficherArticleByProductType(sessionIdTypeProd);
				listeTypeProd = ConnectionMySql.afficherProductTypeByCategory(sessionIdCategorie);	
				// Renvoyer id type de produit choisie
				request.setAttribute("idTypeProduitChoisi", sessionIdTypeProd);
				// Renvoyer id catégorie choisie
				request.setAttribute("idCategorieChoisi", sessionIdCategorie);
				if(sessionOrdre != null) {
					
					request.setAttribute("ordreChoisi", sessionOrdre);
					listeArt = sortArticleListByOrder(listeArt,sessionOrdre);
				}
			} else {
				
				// Afficher tous détails de tous les articles
				listeArt = ConnectionMySql.afficherArticle();
				if(sessionOrdre != null) {
					
					request.setAttribute("ordreChoisi", sessionOrdre);
					listeArt = sortArticleListByOrder(listeArt,sessionOrdre);
				}
			}

			// Renvoyer liste de tous articles
			request.setAttribute("listeArt", listeArt);
			// Renvoyer liste de tous categories
			request.setAttribute("listeCat", listeCat);
			// Renvoyer liste de tous types produit
			request.setAttribute("listeTypeProd", listeTypeProd);

			request.getRequestDispatcher("accueil").forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Trier la liste d'articles par ordre (croissant/décroissant) par prix par kg
	 */
	public ArrayList<Article> sortArticleListByOrder(ArrayList<Article> listeArticle,String order){
		
		// Comparateur en croissant par défaut
        Comparator<Article> comparator = Comparator.comparing(Article::getPrixKgArticle);
        
        // inverser comparateur si ordre=decroissant
        if ("decroissant".equalsIgnoreCase(order)) {
        	
            comparator = comparator.reversed();
        }

        // Trier la liste
        Collections.sort(listeArticle, comparator);

        return listeArticle;
	}

}
