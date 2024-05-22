package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get article id to add
		String idArticle = request.getParameter("idArticle");
		// get article quantity to add from home page
		String quantity = request.getParameter("quantity");
		// get article id to remove
		String idArticleRm = request.getParameter("idArticleRm");
		// get action delete articles from cart
		String action = request.getParameter("action");
		// Gets session
		HttpSession session = request.getSession();

		int countArtCard = 0;

		try {
			// Check for article id (without quantity)
			if (idArticle != null && quantity == null) {
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
			}
			// Check for article id (with quantity)
			else if (idArticle != null && quantity != null && action == null) {
				Article article = ConnectionMySql.getArticleById(idArticle);
				List<Article> articlesInSession = (List<Article>) session.getAttribute("articleList");

				// Check if exists var in session
				if (articlesInSession == null) {
					// If not exists, creates new list of articles and add the quantity to quantity
					// article
					// and add the article
					List<Article> articleList = new ArrayList<>();
					article.setQuantite(article.getQuantite() + Integer.parseInt(quantity));
					articleList.add(article);
					session.setAttribute("articleList", articleList);

					countArtCard = articleList.size();
					session.setAttribute("countArtCard", countArtCard);

					/*----- Type de la r�ponse -----*/
					response.setContentType("application/xml;charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					try (PrintWriter out = response.getWriter()) {
						/*----- Ecriture de la page XML -----*/
						out.println("<?xml version=\"1.0\"?>");
						out.println("<cardHeader>");
						out.println("<int>" + countArtCard + "</int>");
						out.println("</cardHeader>");
					}

				} else {
					// If exists and doesn't has been added to the list, add the quantity to
					// quantity article
					// and add the article
					if (articlesInSession.stream().filter(as -> as.getEAN() == article.getEAN()).findFirst()
							.isEmpty()) {
						article.setQuantite(article.getQuantite() + Integer.parseInt(quantity));
						articlesInSession.add(article);

						countArtCard = articlesInSession.size();
						session.setAttribute("countArtCard", countArtCard);

						/*----- Type de la r�ponse -----*/
						response.setContentType("application/xml;charset=UTF-8");
						response.setCharacterEncoding("UTF-8");
						try (PrintWriter out = response.getWriter()) {
							/*----- Ecriture de la page XML -----*/
							out.println("<?xml version=\"1.0\"?>");
							out.println("<cardHeader>");
							out.println("<int>" + countArtCard + "</int>");
							out.println("</cardHeader>");
						}

					} else {
						// If exists and has been added to the list, add the quantity to quantity
						// article
						articlesInSession.stream().filter(as -> as.getEAN() == article.getEAN()).findFirst()
								.ifPresent(a -> a.setQuantite(a.getQuantite() + Integer.parseInt(quantity)));
					}
					session.setAttribute("articleList", articlesInSession);
				}
			}
			// Check for action of deleting items, if called the parameter, delete the var
			// of session containing all the articles in cart
			else if (idArticleRm != null && idArticleRm != "") {
				Article article = ConnectionMySql.getArticleById(idArticleRm);
				List<Article> articlesInSession = (List<Article>) session.getAttribute("articleList");

				Optional<Article> art = articlesInSession.stream().filter(as -> as.getEAN() == article.getEAN())
						.findFirst();
				if (art.get().getQuantite() - 1 <= 0) {
					articlesInSession.removeIf(as -> as.getEAN() == article.getEAN());

					countArtCard = articlesInSession.size();
					session.setAttribute("countArtCard", countArtCard);

					/*----- Type de la r�ponse -----*/
					response.setContentType("application/xml;charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					try (PrintWriter out = response.getWriter()) {
						/*----- Ecriture de la page XML -----*/
						out.println("<?xml version=\"1.0\"?>");
						out.println("<cardHeader>");
						out.println("<int>" + countArtCard + "</int>");
						out.println("</cardHeader>");
					}

				} else {
					articlesInSession.stream().filter(as -> as.getEAN() == article.getEAN()).findFirst()
							.ifPresent(a -> a.setQuantite(a.getQuantite() - 1));
				}

				session.setAttribute("articleList", articlesInSession);
			}
			// Return the cart page
			else if (action == null) {
				request.getRequestDispatcher("panier").forward(request, response);
			}
			// Get action with more details of the event
			else {
				switch (action) {
				// Delete articles from cart
				case "deleteArticlesCart":
					session.setAttribute("articleList", null);
					
					session.setAttribute("countArtCard", 0);

					/*----- Type de la r�ponse -----*/
					response.setContentType("application/xml;charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					try (PrintWriter out = response.getWriter()) {
						/*----- Ecriture de la page XML -----*/
						out.println("<?xml version=\"1.0\"?>");
						out.println("<cardHeader>");
						out.println("<int>" + 0 + "</int>");
						out.println("</cardHeader>");
					}
					break;
				// On keyup function to change quantity manually
				case "changeArt":
					System.out.println(quantity);
					Article article = ConnectionMySql.getArticleById(idArticle);
					List<Article> articlesInSession = (List<Article>) session.getAttribute("articleList");

					// Change the quantity of article
					articlesInSession.stream().filter(as -> as.getEAN() == article.getEAN()).findFirst()
							.ifPresent(a -> a.setQuantite(Integer.parseInt(quantity)));

					session.setAttribute("articleList", articlesInSession);
					break;
				}
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
		try {
			doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
