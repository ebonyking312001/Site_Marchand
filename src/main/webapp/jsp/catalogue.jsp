<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Article" %>
<%@ page import="bd.ConnectionMySql" %>
<!DOCTYPE html>
<html>
<head>
    <title>Catalogue des Articles</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>EAN</th>
                <th>Vignette</th>
                <th>Prix Unitaire</th>
                <th>Nutriscore</th>
                <th>Libellé</th>
                <th>Poids</th>
                <th>Prix par Kg</th>
                <th>Description Courte</th>
                <th>Description Longue</th>
                <th>Fournisseur</th>
                <th>Marque</th>
                <th>Id Rayon</th>
            </tr>
        </thead>
        <tbody>
            <%
            try {
                ArrayList<Article> articles = ConnectionMySql.afficherArticleCatalogue();
                for (Article article : articles) {
            %>
            <tr>
                <td><%= article.getEAN() %></td>
                <td><%= article.getVignetteArticle() %></td>
                <td><%= article.getPrixUnitaireArticle() %></td>
                <td><%= article.getNutriscoreArticle() %></td>
                <td><%= article.getLibelleArticle() %></td>
                <td><%= article.getPoidsArticle() %></td>
                <td><%= article.getPrixKgArticle() %></td>
                <td><%= article.getDescriptionCourteArticle() %></td>
                <td><%= article.getDescriptionLongueArticle() %></td>
                <td><%= article.getFournisseurArticle() %></td>
                <td><%= article.getMarque() %></td>
                <td><%= article.getIdRayon() %></td>
            </tr>
            <%
                }
            } catch (Exception e) {
                out.println("<p>Erreur : " + e.getMessage() + "</p>");
            }
            %>
        </tbody>
    </table>
    
    <!-- Formulaire d'importation -->
    <form action="ImportServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="file" accept=".csv" required>
        <button type="submit">Importer</button>
    </form>
    
</body>
</html>
