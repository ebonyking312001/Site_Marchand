package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.ConnectionMySql;
import model.Article;
import model.Categorie;

@WebServlet("/catalogue")
public class CtrlCatalogue extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	
            List<Article> articles = ConnectionMySql.afficherArticle();
            List<String> nomCategorie = new ArrayList<String>();
            for (Article article: articles) {
            	if (!nomCategorie.contains(article.getNomCategorie()))
            	nomCategorie.add(article.getNomCategorie());
            }
            
            request.setAttribute("articles", articles);
            request.setAttribute("categories", nomCategorie);
            request.getRequestDispatcher("/jsp/catalogue.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Erreur : " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
