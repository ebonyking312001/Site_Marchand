package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputStream.GetField;
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
import model.Magasin_CreneauRetrait;
import model.User;

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
		 if ( request.getSession().getAttribute("user") == null) {
             request.getRequestDispatcher("/jsp/Authentification.jsp").forward(request, response);
         }else {
		// Gets session
		HttpSession session = request.getSession();

		String nomMagasin = request.getParameter("nomM");
		String heureRetrait = request.getParameter("hRet");
		String dateRetrait = request.getParameter("dtRet");
		String totalPrice = request.getParameter("totalPrice");
		System.out.println("total price="+totalPrice);
		String pointsInput = request.getParameter("pointsInput");
		String newTotalPrice = request.getParameter("newTotalPrice");
		
		// Récupérer session loyalty points
		String sessionLoyaltyPoints = (String) session.getAttribute("sessionLoyaltyPoints");
		// get session utilisateur 
		User sessionClient = (User) session.getAttribute("user");
		
		// set session points input
		if (pointsInput != null) {
			session.setAttribute("sessionPointsInput", pointsInput);
		}
		// set session point fidelite
		if (totalPrice != null) {
			// Convert totalPrice to int
            float totalPriceFloat = Float.parseFloat(totalPrice);
            // set session totalPrice
    		session.setAttribute("totalPrice", totalPriceFloat);
			// Set session loyalty points
			session.setAttribute("sessionLoyaltyPoints", String.valueOf(getLoyaltyPointsBySpending(totalPriceFloat)));
			// Récupérer session loyalty points
			sessionLoyaltyPoints = (String) session.getAttribute("sessionLoyaltyPoints");
		}
		// set session point fidelite si il ya un nouveau prix
		if (newTotalPrice != null) {
			// Convert totalPrice to int
            float newTotalPriceFloat = Float.parseFloat(newTotalPrice);
			// Set session loyalty points pour nouveau points à gagner
			session.setAttribute("sessionLoyaltyPoints", String.valueOf(getLoyaltyPointsBySpending(newTotalPriceFloat)));
			// Récupérer session loyalty points
			sessionLoyaltyPoints = (String) session.getAttribute("sessionLoyaltyPoints");
			
			/*----- Type de la r�ponse -----*/
			response.setContentType("application/xml;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			try (PrintWriter out = response.getWriter()) {
				/*----- Ecriture de la page XML -----*/
				out.println("<?xml version=\"1.0\"?>");
				out.println("<loyaltyPoints>");
				out.println("<points>" + sessionLoyaltyPoints + "</points>");
				out.println("</loyaltyPoints>");
				
			}
		}
		System.out.println("sessionLoyalyPoints="+sessionLoyaltyPoints);
		
		// Récupérer session loyalty points à soustraire
		String sessionPointsInput = (String) session.getAttribute("sessionPointsInput");

		try {
			// afficher points de fidelite lorsqu'un utilisateur est identifie
			if (sessionClient.getId() > 0) {
				// Récuperer infos utilisateurs
				// Afficher point de fidelite a gagner
				request.setAttribute("pointFidelite", sessionLoyaltyPoints);
				// afficher point de fidelite disponible
				request.setAttribute("pointFideliteDispo", sessionClient.getPointsFidelite());
			}
			if (nomMagasin != null && heureRetrait == null && dateRetrait == null) {

				/*----- Type de la r�ponse -----*/
				response.setContentType("application/xml;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				try (PrintWriter out = response.getWriter()) {
					/*----- Ecriture de la page XML -----*/
					out.println("<?xml version=\"1.0\"?>");
					out.println("<horaire_journee>");

					try {
						String horaire = ConnectionMySql.getOpeningByMagasinName(nomMagasin);

						out.println("<hof>" + horaire + "</hof>");
						ArrayList<Magasin_CreneauRetrait> heuresDispo = ConnectionMySql.creneauxDispo(nomMagasin);
						
						for(Magasin_CreneauRetrait mc : heuresDispo) {
							out.println("<hr>"+
									mc.getDebutCreneau().toString().substring(0, 5)+" - "+mc.getFinCreneau().toString().substring(0, 5) +
									" nombre disponible : "+ mc.getNbDispoCreneau()+ 
									"<idC>" + mc.getDebutCreneau().toString().substring(0, 5)+" - "+mc.getFinCreneau().toString().substring(0, 5)+"</idC></hr>");
						}
						
						// set xml nouveau points fidélité à gagner
						out.println("<points>" + sessionLoyaltyPoints + "</points>");
						
					} catch (ClassNotFoundException | SQLException ex) {
					
						out.println("<hof>Erreur - " + ex.getMessage() + "</hof>");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
										
					// ajouter points de fidélité sur le compte de l'utilisateur
					if (sessionLoyaltyPoints != null) {
						int points = Integer.parseInt(sessionLoyaltyPoints);
						ConnectionMySql.updateLoyaltyPoints(sessionClient.getId(), points, "add");
					}
					// enlever points de fidélité sur le compte de l'utilisateur
					if (sessionPointsInput != null) {
						System.out.println("sessionPointsInput="+sessionPointsInput);
						// convert string to int
						int points = Integer.parseInt(sessionPointsInput);
						ConnectionMySql.updateLoyaltyPoints(sessionClient.getId(), points, "substract");
					}
					// ConnectionMySql.addCommande(nomMagasin, d, tDeb, tFin, articlesInSession);
					
					ConnectionMySql.addCommande(nomMagasin, d, tDeb, tFin, articlesInSession,((User) session.getAttribute("user")).getId());
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
						out.println("<points>" + sessionLoyaltyPoints + "</points>");
						out.println("</cardHeader>");
						
					}

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
		}}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	public int getLoyaltyPointsBySpending(float sommeDepense) {
		int discount = 0;
		if ((int)sommeDepense > 5) {
			discount = (int) sommeDepense / 5;
		}
		return discount;
	}

}
