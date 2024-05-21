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
	    String action = request.getParameter("action");

	    if (action == null) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action parameter");
	        return;
	    }

	    try {
	        if (action.equals("afficherCommandes")) {
	            ArrayList<Commande> cEnCours = ConnectionMySql.panierCommande("en cours");
	            request.setAttribute("cEnCours", cEnCours);
	            request.getRequestDispatcher("/jsp/PanierCommande.jsp").forward(request, response);
	        } else {
	            ArrayList<ArticleCommande> cmdD = ConnectionMySql.DetailCommande(action);
	            request.setAttribute("ean", action);
	            request.setAttribute("cmdD", cmdD);
	            request.getRequestDispatcher("/jsp/PanierCommande.jsp").forward(request, response);
	        }
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
		    StringBuilder jsonBuffer = new StringBuilder();
			String line;
			try (BufferedReader reader = request.getReader()) {
			    while ((line = reader.readLine()) != null) {
			        jsonBuffer.append(line);
			    }
			}
			String jsonString = jsonBuffer.toString();
			jsonString = jsonString.replace("[", "").replace("]", "").replace("\"", "");
			String[] eanArray = jsonString.split(",");
			
			ArrayList<String> selectedEANs = new ArrayList<>();
			for (String ean : eanArray) {
			    selectedEANs.add(ean.trim());
			}
			String cmdId = request.getParameter("cmd-id");
			System.out.println(cmdId);
			System.out.println(selectedEANs.get(0));
	
	}

}
