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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        String nomListeCourse = request.getParameter("nomListeCourse");
        String listeIdTp = request.getParameter("listeIdTp");

        if (action == null && nomListeCourse != null) {
            try {
                if(listeIdTp != null) {    
                }

                int idNewListe = ConnectionMySql.addListeCourse(nomListeCourse, null);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                ArrayList<TypeProduit> typesProd = ConnectionMySql.afficherTypeProduit();
                request.setAttribute("typesProduits", typesProd);
                
                ArrayList<ListeCourse> courses = ConnectionMySql.getListesCourses();
                request.setAttribute("listeCourses", courses);
                
                // Récupérer les détails de chaque liste de courses
                for (ListeCourse course : courses) {
                    int idListe = course.getIdListe();
                    ArrayList<ContenuListe> contenuTypeProduit = ConnectionMySql.getContenuListeByIdForTypeProduit(idListe);
                    ArrayList<ContenuListe> contenuArticle = ConnectionMySql.getContenuListeByIdForArticle(idListe);
                    course.setContenuTypeProduit(contenuTypeProduit);
                    course.setContenuArticle(contenuArticle);
                }

            } catch (ClassNotFoundException | SQLException e) {
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
