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
		// get article id
		String idArticle = request.getParameter("idArticle");
		try {
			HttpSession session = request.getSession();
			Article article = ConnectionMySql.getArticleById(idArticle);
			List<Article> articlesInSession = (List<Article>) session.getAttribute("articleList");

			if (articlesInSession == null) {
				List<Article> articleList = new ArrayList<>();
				articleList.add(article);
				session.setAttribute("articleList", articleList);
			} else {
				if (articlesInSession.contains(articlesInSession.stream()
						.filter(as -> as.getEAN() == Integer.parseInt(idArticle)).findAny().get())) {
					articlesInSession.stream().filter(as -> as.getEAN() == Integer.parseInt(idArticle)).findFirst()
							.ifPresent(a -> a.setQuantite(a.getQuantite() + 1));
				} else {
					articlesInSession.add(article);
				}
				session.setAttribute("articleList", articlesInSession);
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
