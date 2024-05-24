package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.ConnectionMySql;
import model.Article;
import model.ContenuListe;
import model.ListeCourse;
import model.TypeProduit;

/**
 * Servlet implementation class CtrlListeCourse
 */
@WebServlet("/ServletListeCourse")
public class CtrlListeCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String nomListeCourse = request.getParameter("nomListeCourse");
		String listeIdTp = request.getParameter("listeIdTp");
		String idListeC = request.getParameter("idListeCourse");
		String action = request.getParameter("action");

		if (nomListeCourse != null && idListeC == null && action == null) {
			if (listeIdTp != null) {
				String[] listeIds = listeIdTp.split("_");
				ArrayList<String> listIdStrings = new ArrayList<>();
				for (String id : listeIds) {
					listIdStrings.add(id);
				}
				try {
					int idNewListe = ConnectionMySql.addListeCourse(nomListeCourse, listIdStrings);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					int idNewListe = ConnectionMySql.addListeCourse(nomListeCourse, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (idListeC != null && action == null) {
			try {

				ConnectionMySql.deleteListeCourseById(idListeC);

				System.out.println("ok delete liste");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (idListeC != null && action != null) {
			try {
				ArrayList<Article> articlesAAjouter = ConnectionMySql
						.getArticlesInfoByListeCourseId(Integer.parseInt(idListeC));

				List<Article> articlesInSession = (List<Article>) session.getAttribute("articleList");

				int countArtCard = 0;

				if (articlesInSession == null) {
					List<Article> articleList = new ArrayList<>();
					for (Article art : articlesAAjouter) {
						if (articleList.stream().filter(as -> as.getEAN() == art.getEAN()).findFirst().isEmpty()) {
							articleList.add(art);

						} else {
							articleList.stream().filter(as -> as.getEAN() == art.getEAN()).findFirst()
									.ifPresent(a -> a.setQuantite(a.getQuantite() + art.getQuantite()));
						}
					}

					countArtCard = articleList.size();
					session.setAttribute("articleList", articleList);
					session.setAttribute("countArtCard", countArtCard);

					/*----- Type de la réponse -----*/
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
					for (Article art : articlesAAjouter) {
						if (articlesInSession.stream().filter(as -> as.getEAN() == art.getEAN()).findFirst()
								.isEmpty()) {
							articlesInSession.add(art);

						} else {
							articlesInSession.stream().filter(as -> as.getEAN() == art.getEAN()).findFirst()
									.ifPresent(a -> a.setQuantite(a.getQuantite() + art.getQuantite()));
						}
					}

					countArtCard = articlesInSession.size();
					session.setAttribute("articleList", articlesInSession);
					session.setAttribute("countArtCard", countArtCard);

					/*----- Type de la réponse -----*/
					response.setContentType("application/xml;charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					try (PrintWriter out = response.getWriter()) {
						/*----- Ecriture de la page XML -----*/
						out.println("<?xml version=\"1.0\"?>");
						out.println("<cardHeader>");
						out.println("<int>" + countArtCard + "</int>");
						out.println("</cardHeader>");
					}
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			try {
				ArrayList<TypeProduit> typesProd = ConnectionMySql.afficherTypeProduit();
				request.setAttribute("typesProduits", typesProd);

				ArrayList<ListeCourse> courses = ConnectionMySql.getListesCourses();
				request.setAttribute("listeCourses", courses);
				for (ListeCourse course : courses) {
					int idListe = course.getIdListe();
					ArrayList<ContenuListe> contenuTypeProduit = ConnectionMySql
							.getContenuListeByIdForTypeProduit(idListe);
					ArrayList<ContenuListe> contenuArticle = ConnectionMySql.getContenuListeByIdForArticle(idListe);
					course.setContenuTypeProduit(contenuTypeProduit);
					course.setContenuArticle(contenuArticle);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("ListeCourse").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
