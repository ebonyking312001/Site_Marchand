package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Article;

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

		// Creates new session
		HttpSession session = request.getSession();

		request.getRequestDispatcher("panier").forward(request, response);

		// Gets the article selected to add to card
		String idArticle = request.getParameter("checkedBoxPanier");

		// Check if article is not null
		if (idArticle != null) {
			// Gets Article by Id
			
			// Gets the current list of articles in the card
			ArrayList<Article> articlesToAddToSession = (ArrayList<Article>) session.getAttribute("articleSession");
			if(articlesToAddToSession != null) {
				// Adds the article to the current session
//				articlesToAddToSession.add();
			}
			else {
				//Creates a new list of articles and adds the article
				articlesToAddToSession = new ArrayList<>();
//				articlesToAddToSession.add();
			}
			
			// Sets the new list of articles for the session
			session.setAttribute("articlesSession", articlesToAddToSession);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub

	}

}
