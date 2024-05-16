package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

/**
 * Cette servlet retourne un flux XML.
 */

@WebServlet(value="/CtrlGestionMarcServlet")
@MultipartConfig
public class CtrlGestionMarcServlet extends HttpServlet
{
	
	    public List<Article> readCSV(InputStream fileContent) throws IOException {
	        List<Article> articles = new ArrayList<>();
	        
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent))) {
	            String line;
	            // Skip header if exists
	            reader.readLine();
	            while ((line = reader.readLine()) != null) {
	            	
	            	
	                String[] values = line.split(",");
	                
	                if (values.length == 12) {
	                	
	                try {
	                	System.out.println("encore en teeeest");
	                	int EAN = Integer.parseInt(values[0].replace("\"", "").trim());
	                	
                        String vignetteArticle = values[1].replace("\"", "").trim();
                        Float prixUnitaireArticle = Float.parseFloat(values[2].replace("\"", "").trim());
                        String NutriscoreArticle = values[3].replace("\"", "").trim();
                        String libelleArticle = values[4].replace("\"", "").trim();
                        Float poidsArticle = Float.parseFloat(values[5].replace("\"", "").trim());
                        Float prixKgArticle = Float.parseFloat(values[6].replace("\"", "").trim());
                        String descriptionCourteArticle = values[7].replace("\"", "").trim();
                        String descriptionLongueArticle = values[8].replace("\"", "").trim();
                        String fournisseurArticle = values[9].replace("\"", "").trim();
                        String marque = values[10].replace("\"", "").trim();
                        int promoArticle = Integer.parseInt(values[11].replace("\"", "").trim());
                        int idRayon = Integer.parseInt(values[12].replace("\"", "").trim());
	                
	                Article article = new Article(EAN, vignetteArticle, prixUnitaireArticle,
                            NutriscoreArticle, libelleArticle, poidsArticle, prixKgArticle,
                            descriptionCourteArticle, descriptionLongueArticle, fournisseurArticle,
                            marque, promoArticle, idRayon);
	                
	                articles.add(article);
	                System.out.println("Article bien ajouté");
	                }
	                catch(NumberFormatException e){
	                	System.err.println("Erreur de format de nombre : " + e.getMessage());
	                }
	                }
	                // Create Article object and add to list
	                
	                
	            }
	        }
	        
	        return articles;
	    }
	    
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			
		}

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
    Part filePart = request.getPart("file");
    InputStream fileContent = filePart.getInputStream();
    
    List<Article> articles = readCSV(fileContent);
    
    try {
        for (Article article : articles) {
            // Insérer chaque article dans la base de données
            ConnectionMySql.insererArticle(article);
        }
        
        // Récupérer la liste mise à jour des articles depuis la base de données
        articles = ConnectionMySql.afficherArticle();
        
        // Rediriger vers une page qui affiche la liste mise à jour des articles
        request.setAttribute("articles", articles);
        request.getRequestDispatcher("catalogue").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
        response.getWriter().println("Erreur : " + e.getMessage());
    }

}

	}
 /*----- Fin de la servlet ServletAuteur -----*/
