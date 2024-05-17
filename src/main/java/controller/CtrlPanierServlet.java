package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

		// get article id
		String idArticle = request.getParameter("idArticle");
		// get action delete articles from cart
		String deleteArticles = request.getParameter("action");
		// Gets session
		HttpSession session = request.getSession();

		try {
			// Check for article id
			if (idArticle != null && idArticle != "") {
				Article article = ConnectionMySql.getArticleById(idArticle);
				List<Article> articlesInSession = (List<Article>) session.getAttribute("articleList");

				// Check if exists var in session
				if (articlesInSession == null) {
					// If not exists, creates new list of articles and add +1 to quantity article
					// and add the article
					List<Article> articleList = new ArrayList<>();
					article.setQuantite(article.getQuantite() + 1);
					articleList.add(article);
					session.setAttribute("articleList", articleList);
				} else {
					// If exists and doesn't has been added to the list, add +1 to quantity article
					// and add the article
					if (articlesInSession.stream().filter(as -> as.getEAN() == article.getEAN()).findFirst()
							.isEmpty()) {
						article.setQuantite(article.getQuantite() + 1);
						articlesInSession.add(article);
					} else {
						// If exists and has been added to the list, add +1 to quantity article
						articlesInSession.stream().filter(as -> as.getEAN() == article.getEAN()).findFirst()
								.ifPresent(a -> a.setQuantite(a.getQuantite() + 1));
					}
					session.setAttribute("articleList", articlesInSession);
				}
				// Check for action of deleting items, if called the parameter, delete the var
				// of session containing all the articles in cart
			} else if (deleteArticles == null) {
				request.getRequestDispatcher("panier").forward(request, response);
			} else {
				// Return the cart page (in all other cases)
				session.setAttribute("articleList", null);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

	}

}
