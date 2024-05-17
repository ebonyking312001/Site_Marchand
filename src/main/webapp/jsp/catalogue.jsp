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
                <th style="display: none;">Vignette</th>
                <th>Prix Unitaire</th>
                <th>Nutriscore</th>
                <th>Libellé</th>
                <th>Poids</th>
                <th>Prix par Kg</th>
                <th>Description Courte</th>
                <th>Description Longue</th>
                <th>Fournisseur</th>
                <th>Marque</th>
                <th>Promo Article</th>
                <th>Id Rayon</th>
                <th>Categorie</th>
                <th>TypeProduit</th>
            </tr>
        </thead>
        <tbody>
            <%
            try {
                ArrayList<Article> articles = ConnectionMySql.afficherArticle();
                for (Article article : articles) {
            %>
            <tr>
                <td><%= article.getEAN() %></td>
                <td style="display: none;"><%= article.getVignetteArticle() %></td>
                <td><%= article.getPrixUnitaireArticle() %></td>
                <td><%= article.getNutriscoreArticle() %></td>
                <td><%= article.getLibelleArticle() %></td>
                <td><%= article.getPoidsArticle() %></td>
                <td><%= article.getPrixKgArticle() %></td>
                <td><%= article.getDescriptionCourteArticle() %></td>
                <td><%= article.getDescriptionLongueArticle() %></td>
                <td><%= article.getFournisseurArticle() %></td>
                <td><%= article.getMarque() %></td>
                <td><%= article.getPromoArticle() %></td>
                <td><%= article.getIdRayon() %></td>
                <td><%= article.getIdCategorie() %></td>
                <td><%= article.getIdTypeProduit() %></td>
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
    <form action="CtrlGestionMarcServlet" method="POST" enctype="multipart/form-data">
        <input type="file" name="file" accept=".csv" required>
        <button type="submit">Importer</button>
    </form>
    
</body>
</html>
