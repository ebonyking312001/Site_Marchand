package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Article;
import bd.ConnectionMySql;

/**
 * Servlet implementation class ServletAjout
 */
@WebServlet("/ServletPanier")
public class CtrlPanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Stocker la liste d'articles dans la session
		HttpSession session = request.getSession();

		// Créer une liste d'articles pour test
        List<Article> articleList = new ArrayList<>();

		// Creates new Article
		Article getArticle = new Article();

		// Gets the current list of articles in the card
		ArrayList<Article> articlesToAddToSession = (ArrayList<Article>) session.getAttribute("articleSession");

		// Gets the article selected to add to card
		String idArticle = request.getParameter("checkedBoxPanier");

//		request.getRequestDispatcher("panier").forward(request, response);

        articleList.add(new Article(1, "Vignette1", 10.99f, "A", "Article 1", 0.5f, 21.98f, "Description courte 1", "Description longue 1", "Fournisseur 1", "Marque 1", 1));
        articleList.add(new Article(2, "Vignette2", 20.49f, "B", "Article 2", 0.75f, 27.32f, "Description courte 2", "Description longue 2", "Fournisseur 2", "Marque 2", 2));
        articleList.add(new Article(3, "Vignette3", 15.75f, "C", "Article 3", 0.6f, 26.25f, "Description courte 3", "Description longue 3", "Fournisseur 3", "Marque 3", 3));

        session.setAttribute("articleList", articleList);	

		request.getRequestDispatcher("panier").forward(request, response);

//		// Check if article is not null
//		if (idArticle != null) {
//			// Gets Article by Id
//			try {
//				getArticle = ConnectionMySql.getArticleById(idArticle);
//
//				if (getArticle != null) {
//					if (articlesToAddToSession != null) {
//						// Adds the article to the current session
//						articlesToAddToSession.add(getArticle);
//					} else {
//						// Creates a new list of articles and adds the article
//						articlesToAddToSession = new ArrayList<>();
//						articlesToAddToSession.add(getArticle);
//					}
//				}
//
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			// Sets the new list of articles for the session
//			session.setAttribute("articlesSession", articlesToAddToSession);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub

	}

}
