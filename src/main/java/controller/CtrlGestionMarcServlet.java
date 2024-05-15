package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
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
@WebServlet(value="/ServletGestion")
public class CtrlGestionMarcServlet extends HttpServlet
{
	
	
	public class CSVReader {

	    public List<Article> readCSV(InputStream fileContent) throws IOException {
	        List<Article> articles = new ArrayList<>();
	        
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent))) {
	            String line;
	            // Skip header if exists
	            reader.readLine();
	            while ((line = reader.readLine()) != null) {
	                String[] values = line.split(","); // assuming CSV is comma-separated
	                
	                // Assuming the order of columns in CSV matches the order of fields in Article class
	                double EAN = Double.parseDouble(values[0].trim());
	                String vignetteArticle = values[1].trim();
	                double prixUnitaireArticle = Double.parseDouble(values[2].trim());
	                String NutriscoreArticle = values[3].trim();
	                String libelleArticle = values[4].trim();
	                double poidsArticle = Double.parseDouble(values[5].trim());
	                double prixKgArticle = Double.parseDouble(values[6].trim());
	                String descriptionCourteArticle = values[7].trim();
	                String descriptionLongueArticle = values[8].trim();
	                String fournisseurArticle = values[9].trim();
	                String marque = values[10].trim();
	                int idRayon = Integer.parseInt(values[11].trim());
	                
	                // Create Article object and add to list
//	                Article article = new Article(EAN, vignetteArticle, prixUnitaireArticle,
//	                                              NutriscoreArticle, libelleArticle, poidsArticle, prixKgArticle,
//	                                              descriptionCourteArticle, descriptionLongueArticle, fournisseurArticle,
//	                                              marque, idRayon);
//	                articles.add(article);
	            }
	        }
	        
	        return articles;
	    }
	    
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
		    Part filePart = request.getPart("file");
		    InputStream fileContent = filePart.getInputStream();
		    
		    List<Article> articles = readCSV(fileContent);
		    
		    try {
		        for (Article article : articles) {
		            // Ins�rer chaque article dans la base de donn�es
		            // ConnectionMySql.insertArticle(article);
		        }
		        
		        // R�cup�rer la liste mise � jour des articles depuis la base de donn�es
		        articles = ConnectionMySql.afficherArticleCatalogue();
		        
		        // Rediriger vers une page qui affiche la liste mise � jour des articles
		        request.setAttribute("articles", articles);
		        request.getRequestDispatcher("display.jsp").forward(request, response);
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.getWriter().println("Erreur : " + e.getMessage());
		    }
		

		}

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doGet(request, response); }
	}
} /*----- Fin de la servlet ServletAuteur -----*/
