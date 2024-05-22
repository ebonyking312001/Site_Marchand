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

import bd.ConnectionMySql;
import model.Article;


/**
 * Servlet implementation class CtrlRepArticleCategorieServlet
 */
@WebServlet("/CtrlRepArticleCategorieServlet")
public class CtrlRepArticleCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrlRepArticleCategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*----- Type de la réponse -----*/
		response.setContentType("application/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try (PrintWriter out = response.getWriter()){
			
			String action = request.getParameter("action");
            System.out.println(action);
		}/*----- Ecriture de la page XML -----*/
		out.println("<?xml version=\"1.0\"?>");
		out.println("<google>");
		
		String idCat = request.getParameter("idCat");
		// Vérifier si le mot existe déjà
		boolean exist = false;
		
		try {
			/*----- Lecture de liste de mots dans la BD -----*/
			ArrayList<Articles> motsTrouve = ConnectionMySql.afficherArticleByProductType(idTypeProd);
			
			if (motsTrouve.isEmpty()) {
				
			} else {
				exist = true;
				out.println("<exist>" + exist + "</exist>");
				for (String mot : motsTrouve) {
					out.println("<mot>" + mot + "</mot>");
				}
			}
			
		} catch (ClassNotFoundException | SQLException ex)
			{
			out.println("<mot>Erreur - " + ex.getMessage() + "</mot>");
			}
		
		System.out.println("</google>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
