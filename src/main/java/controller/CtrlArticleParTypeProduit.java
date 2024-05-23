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
       int idTypeProduit = Integer.parseInt(request.getParameter("idTypeProduit"));
        try {
            ArrayList<Article> articles = ConnectionMySql.getAllArticlesByTypeProduit(idTypeProduit);
            String nomTypeProduit = articles.get(0).getNomTypeProduit();

            request.setAttribute("articles", articles);
            request.setAttribute("nomTypeProduit", nomTypeProduit);

            request.getRequestDispatcher("ArticleTypeProduit").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
