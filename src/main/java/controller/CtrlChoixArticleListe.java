package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.ConnectionMySql;

/**
 * Servlet implementation class CtrlChoixArticleListe
 */
@WebServlet("/CtrlChoixArticleListe")
public class CtrlChoixArticleListe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je rentre");
		String idListe = request.getParameter("idListe");
		String idTypeProduit = request.getParameter("idTypeProduit");
		String EAN = request.getParameter("EAN");
		String quantite = request.getParameter("quantite");
		
        try {
			ConnectionMySql.updateContenuListe(Integer.parseInt(EAN), Integer.parseInt(quantite),Integer.parseInt(idListe), Integer.parseInt(idTypeProduit));
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.getRequestDispatcher("/ServletListeCourse").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
