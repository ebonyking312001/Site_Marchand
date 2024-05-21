package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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
import model.Magasin;

/**
 * Servlet implementation class CtrlConfirmationPanierServlet
 */
@WebServlet("/ConfirmationPanierServlet")
public class CtrlConfirmationPanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Gets session
		HttpSession session = request.getSession();

		String nomMagasin = request.getParameter("nomM");
		String heureRetrait = request.getParameter("hRet");
		String dateRetrait = request.getParameter("dtRet");

		try {
			if (nomMagasin != null && heureRetrait == null && dateRetrait == null) {

				/*----- Type de la réponse -----*/
				response.setContentType("application/xml;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				try (PrintWriter out = response.getWriter()) {
					/*----- Ecriture de la page XML -----*/
					out.println("<?xml version=\"1.0\"?>");
					out.println("<horaire_journee>");

					try {
						/*----- Lecture de liste de mots dans la BD -----*/
						String horaire = ConnectionMySql.getOpeningByMagasinName(nomMagasin);

						out.println("<hof>" + horaire + "</hof>");
						ArrayList<String> heuresDispo = ConnectionMySql.getHoursOpenedByMagasinId(nomMagasin);
						
						for(String h : heuresDispo) {
							out.println("<hr>" + h + "</hr>");
						}
						
					} catch (ClassNotFoundException | SQLException ex) {
						out.println("<hof>Erreur - " + ex.getMessage() + "</hof>");
					}

					out.println("</horaire_journee>");
				}
			} else if (nomMagasin != null && heureRetrait != null && dateRetrait != null) {
				List<Article> articlesInSession = (List<Article>) session.getAttribute("articleList");

				try {
					String[] dateSplit = dateRetrait.split("-");
					String dateFormated = dateSplit[0] + "-" + dateSplit[1] + "-" + dateSplit[2];

					String[] heureSplit = heureRetrait.split(" ");
					
					String hDeb = heureSplit[0];
					String hFin = heureSplit[2];
					
					Date d = java.sql.Date.valueOf(dateFormated);
					Time tDeb = java.sql.Time.valueOf(hDeb + ":00");
					Time tFin = java.sql.Time.valueOf(hFin + ":00");
					
					ConnectionMySql.addCommande(nomMagasin, d, tDeb, tFin, articlesInSession);
					session.setAttribute("articleList", null);

//					request.getRequestDispatcher("accueil").forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				ArrayList<Magasin> magasins = ConnectionMySql.getAllMagasins();

				request.setAttribute("allMagasins", magasins);
				request.getRequestDispatcher("jsp/ConfirmationPanier.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
