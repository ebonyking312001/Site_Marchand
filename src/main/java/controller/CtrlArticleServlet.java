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


/**
 * Servlet implementation class ServletAjout
 */
@WebServlet("/")
public class CtrlArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		// get article id 
		String idArticle = request.getParameter("idArticle");
		try {
			// Afficher tous détails de tous les articles
			ArrayList<Article> listeArt = ConnectionMySql.afficherArticle();
		
			// Renvoyer détails d'un article quand un article est choisi
			if (idArticle != null) {
				// Récupérer info article par id article
				Article article = ConnectionMySql.getArticleById(idArticle);
				
				request.setAttribute("article",article);
				request.getRequestDispatcher("Details").forward(request, response);
				
			}
			// Renvoyer liste de tous articles
			request.setAttribute("listeArt",listeArt);
			request.getRequestDispatcher("/jsp/Accueil.jsp").forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
	
	}

}
