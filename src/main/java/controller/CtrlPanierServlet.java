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
	    	}
	    	else {
				articlesInSession.add(article);
		        session.setAttribute("articleList", articlesInSession);
	    	}

			request.getRequestDispatcher("panier").forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
