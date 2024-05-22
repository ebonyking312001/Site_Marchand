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

	    StringBuilder requestBody = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        requestBody.append(line);
	    }


	    String[] params = requestBody.toString().split("&");
	    String cmdId = null;
	    String ean = null;
	    String etat = null;
	    for (String param : params) {
	        String[] keyValue = param.split("=");
	        if (keyValue.length == 2) {
	            String key = keyValue[0];
	            String value = keyValue[1];
	            if ("cmdId".equals(key)) {
	                cmdId = value;
	            } else if ("ean".equals(key)) {
	                ean = value;
	            } else if ("etat".equals(key)) {
	                etat = value;
	            }
	        }
	    }
	    System.out.println("cmd"+cmdId);
	    System.out.println("etat"+etat);
	    System.out.println("ean"+ean);
	    try {

	        ConnectionMySql.miseAJourCommande(cmdId, ean, etat);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    response.sendRedirect(request.getContextPath() + "/CtrlDetailCommandeAdamServlet/" + cmdId);

	}
}
