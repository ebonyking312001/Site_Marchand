package controller;


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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String action=request.getParameter("action");
		if (action.equals("afficherCommandes")) {
			ArrayList<Commande> cEnCours;
			try {
				cEnCours = ConnectionMySql.panierCommande("en cours");
				request.setAttribute("cEnCours", cEnCours);
		        request.getRequestDispatcher("/PanierCommande").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else {
			ArrayList<ArticleCommande> cmdD;
			try {
				cmdD = ConnectionMySql.DetailCommande(action);
				request.setAttribute("cmdD", cmdD);
		        request.getRequestDispatcher("/PanierCommande").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
	
	}

}
