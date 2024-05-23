package controller;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.ConnectionMySql;
import model.User;

/**
 * Servlet implementation class CtrlAuthentification
 */
@WebServlet("/CtrlAuthentification")
public class CtrlAuthentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrlAuthentification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        request.getRequestDispatcher("/jsp/Authentification.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        try {
	            User user = ConnectionMySql.authenticate(email, password);

	            if (user != null) {
	                javax.servlet.http.HttpSession session = request.getSession();
	                session.setAttribute("user", user);
	                if (user.getEmail().equals("adam@gmail.com")) {
	                    response.sendRedirect("ServletPreparation");
	                } else if (user.getEmail().equals("marc@gmail.com")) {
	                    response.sendRedirect("catalogue");
	                } else {
	                    response.sendRedirect("sitemarchand");
	                }

	            	
	            } else {
	                request.setAttribute("errorMessage", "Invalid email or password");
	                request.getRequestDispatcher("/jsp/Authentification.jsp").forward(request, response);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "An error occurred during authentication. Please try again.");
	            request.getRequestDispatcher("/jsp/Authentification.jsp").forward(request, response);
	        }
	    }

}
