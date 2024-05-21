package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		// get article id
		String idArticle = request.getParameter("idArticle");
		// get category id
		String idCategorie = request.getParameter("idCategorie");
		// get product type id
		String idTypeProd = request.getParameter("idTypeProd");
		
		try {
			
			// Afficher toutes les catégories
			ArrayList<Categorie> listeCat = ConnectionMySql.afficherCategorie();
			// Afficher tous les types produit
			ArrayList<TypeProduit> listeTypeProd = ConnectionMySql.afficherTypeProduit();

			// Renvoyer détails d'un article quand un article est choisi
			if (idArticle != null) {
				// Récupérer info article par id article
				Article article = ConnectionMySql.getArticleById(idArticle);

				request.setAttribute("article", article);
				request.getRequestDispatcher("/jsp/Details.jsp").forward(request, response);
				return;
			}
			ArrayList<Article> listeArt = null;
			// Afficher article par rapport aux filtres catégories, types produits
			if (idCategorie != null) {
				listeArt = ConnectionMySql.afficherArticleByCategory(idCategorie);
				System.out.println("liste d'articles changé : filtre par idCatégorie " + idCategorie + "/n" + listeArt);
			} else if (idTypeProd != null) {
				listeArt = ConnectionMySql.afficherArticleByProductType(idTypeProd);
				System.out.println("liste d'articles changé : filtre par idCatégorie " + idTypeProd + "/n" + listeArt);

			} else {
				// Afficher tous détails de tous les articles
				 listeArt = ConnectionMySql.afficherArticle();
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

}
