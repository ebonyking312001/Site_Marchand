package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bd.ConnectionMySql;
import model.Article;

@WebServlet("/importCSV")
@MultipartConfig
public class CtrlGestionMarcServlet extends HttpServlet {

    public List<Article> readCSV(InputStream fileContent) throws IOException {
        List<Article> articles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent))) {
            String line;
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 15) {
                    try {
                        int EAN = Integer.parseInt(values[0].replace("\"", "").trim());
                        String vignetteArticle = values[1].replace("\"", "").trim();
                        float prixUnitaireArticle = Float.parseFloat(values[2].replace("\"", "").trim());
                        String NutriscoreArticle = values[3].replace("\"", "").trim();
                        String libelleArticle = values[4].replace("\"", "").trim();
                        float poidsArticle = Float.parseFloat(values[5].replace("\"", "").trim());
                        float prixKgArticle = Float.parseFloat(values[6].replace("\"", "").trim());
                        String descriptionCourteArticle = values[7].replace("\"", "").trim();
                        String descriptionLongueArticle = values[8].replace("\"", "").trim();
                        String fournisseurArticle = values[9].replace("\"", "").trim();
                        String marque = values[10].replace("\"", "").trim();
                        int promoArticle = Integer.parseInt(values[11].replace("\"", "").trim());
                        int idRayon = Integer.parseInt(values[12].replace("\"", "").trim());
                        int idCategorie = Integer.parseInt(values[13].replace("\"", "").trim());
                        int idTypeProduit = Integer.parseInt(values[14].replace("\"", "").trim());

                        Article article = new Article(EAN, vignetteArticle, prixUnitaireArticle, NutriscoreArticle,
                                libelleArticle, poidsArticle, prixKgArticle, descriptionCourteArticle,
                                descriptionLongueArticle, fournisseurArticle, marque, promoArticle, idRayon,
                                idCategorie, idTypeProduit);

                        articles.add(article);
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de format de nombre : " + e.getMessage());
                    }
                }
            }
        }
        return articles;
    }
    
    private boolean articleExists(String nomArticle, String fournisseurArticle) {
        try {
            List<Article> articles = ConnectionMySql.afficherArticle();
            for (Article article : articles) {
                if (article.getLibelleArticle().equalsIgnoreCase(nomArticle) && 
                    article.getFournisseurArticle().equalsIgnoreCase(fournisseurArticle)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileContent = filePart.getInputStream();
            List<Article> articles = readCSV(fileContent);
            boolean articleExists = false;
            
            try {
                for (Article article : articles) {
                    if (!articleExists(article.getLibelleArticle(), article.getFournisseurArticle())) {
                        ConnectionMySql.insererArticle(article);
                    } else {
                        articleExists = true;
                    }
                }
                if (articleExists) {
                    request.setAttribute("articleExists", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Erreur : " + e.getMessage());
                return;
            }
        }
        request.getRequestDispatcher("catalogue").forward(request, response);
    }
}
