package controller;


import java.io.BufferedReader;
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
import model.Commande;
import model.ArticleCommande;


/**
 * Servlet implementation class ServletAjout
 */
@WebServlet("/ServletPreparation")
public class CtrlPreparationAdamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	            ArrayList<Commande> cEnCours = ConnectionMySql.panierCommande("en cours");
	            request.setAttribute("cEnCours", cEnCours);
	            request.getRequestDispatcher("/jsp/PanierCommande.jsp").forward(request, response);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal error occurred while processing the request.");
	    }
	}


	

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String cmdId = request.getParameter("cmdId");
	    String ean = request.getParameter("ean");
	    String etat = request.getParameter("etat");
	   
	    
	    try {

	        ConnectionMySql.miseAJourCommande(cmdId, ean, etat);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    response.sendRedirect(request.getContextPath() + "/CtrlDetailCommandeAdamServlet/" + cmdId);
	    return ; 
	}
}
