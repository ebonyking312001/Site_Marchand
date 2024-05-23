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
import javax.servlet.http.HttpSession;

import bd.ConnectionMySql;
import model.Article;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Gets session
		HttpSession session = request.getSession();

		String action = request.getParameter("action");
		String nomListeCourse = request.getParameter("nomListeCourse");
		String listeIdTp = request.getParameter("listeIdTp");

		if (action == null && nomListeCourse != null) {
			try {
<<<<<<< Updated upstream
				if(listeIdTp != null) {
					
				}
				int idNewListe = ConnectionMySql.addListeCourse(nomListeCourse);

=======
				int idNewListe = ConnectionMySql.addListeCourse(nomListeCourse, null);

				/*----- Type de la r�ponse -----*/
//				response.setContentType("application/xml;charset=UTF-8");
//				response.setCharacterEncoding("UTF-8");
//				try (PrintWriter out = response.getWriter()) {
//					/*----- Ecriture de la page XML -----*/
//					out.println("<?xml version=\"1.0\"?>");
//					out.println("<intIdListe>");
//					out.println("<id>" + idNewListe + "</id>");
//
//					for (TypeProduit tp : typesProd) {
//						out.println("<tProd>" + tp.getNomTypeProduit() + "</tProd>");
//					}
//					out.println("</intIdListe>");
//				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (nomTP != null) {
			try {
				ArrayList<Article> articles = ConnectionMySql.getArticlesByTPName(nomTP);

				/*----- Type de la r�ponse -----*/
				response.setContentType("application/xml;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				try (PrintWriter out = response.getWriter()) {
					/*----- Ecriture de la page XML -----*/
					out.println("<?xml version=\"1.0\"?>");
					out.println("<nomArt>");

					for (Article a : articles) {
						out.println("<nArt>" + a.getLibelleArticle() + "</nArt>");
					}
					out.println("</nomArt>");
				}
>>>>>>> Stashed changes
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
