package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        // Créer une liste d'articles pour test
        List<Article> articleList = new ArrayList<>();

        // Ajouter quelques articles factices
        articleList.add(new Article("Textes complets 1", 1234567890123.0, "Vignette1", 10.99, "A", "Article 1", 0.5, 21.98, "Description courte 1", "Description longue 1", "Fournisseur 1", "Marque 1", 1));
        articleList.add(new Article("Textes complets 2", 1234567890124.0, "Vignette2", 20.49, "B", "Article 2", 0.75, 27.32, "Description courte 2", "Description longue 2", "Fournisseur 2", "Marque 2", 2));
        articleList.add(new Article("Textes complets 3", 1234567890125.0, "Vignette3", 15.75, "C", "Article 3", 0.6, 26.25, "Description courte 3", "Description longue 3", "Fournisseur 3", "Marque 3", 3));

        // Stocker la liste d'articles dans la session
        HttpSession session = request.getSession();
        session.setAttribute("articleList", articleList);	
		request.getRequestDispatcher("panier").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub

	}

}
