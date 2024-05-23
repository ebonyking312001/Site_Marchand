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

		String nomListeCourse = request.getParameter("nomListeCourse");
		String listeIdTp = request.getParameter("listeIdTp");

		if (nomListeCourse != null) {
			if(listeIdTp != null) {
				String[] listeIds = listeIdTp.split("_");
				ArrayList<String> listIdStrings = new ArrayList<>();
				for(String id : listeIds) {
					listIdStrings.add(id);
				}
				try {
					int idNewListe = ConnectionMySql.addListeCourse(nomListeCourse, listIdStrings);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else  {
				try {
					int idNewListe = ConnectionMySql.addListeCourse(nomListeCourse, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
