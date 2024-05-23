package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.ConnectionMySql;
import model.Article;

@WebServlet("/ListeArticlesParTypeProduit/*")
public class CtrlArticleParTypeProduit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String  mm = request.getPathInfo();
        String test = mm.substring(1);
        String[] val1 = test.split("/");

        if (val1.length == 3) {
        	System.out.println("REUSSIIII");
        	request.setAttribute("idListe", Integer.parseInt(val1[0]));
            request.setAttribute("idTypeProduit", Integer.parseInt(val1[1]));
            request.setAttribute("EAN", Integer.parseInt(val1[2]));
            request.getRequestDispatcher("CtrlChoixArticleListe").forward(request, response);
        } else {
    
		
        try {
            ArrayList<Article> articles = ConnectionMySql.getAllArticlesByTypeProduit(Integer.parseInt(val1[1]));
            System.out.println(articles);
            String nomTypeProduit = articles.get(0).getNomTypeProduit();
            
            request.setAttribute("articles", articles);
            request.setAttribute("nomTypeProduit", nomTypeProduit);
            request.setAttribute("idListe", Integer.parseInt(val1[0]));
            request.setAttribute("idTypeProduit", Integer.parseInt(val1[1]));
            
            request.getRequestDispatcher("/jsp/ArticleTypeProduit.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
