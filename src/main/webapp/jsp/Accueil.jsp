<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.Article"
    import="java.util.ArrayList"
 	import="bd.ConnectionMySql"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Page d'accueil</h1>

	<table border = "1">
	    <tr>
	    <th>EAN</th>
	    <th>Vignette</th>
	    <th>Prix unitaire</th>
	    <th>Nutriscore</th>
	    <th>Libelle</th>
	    <th>Poids</th>
	    <th>Prix au Kg</th>
	    <th>Description courte</th>
	    <th>Description longue</th>
	    <th>Fournisseur</th>
	    <th>Marque</th>
	    </tr>
	    
	   	<% 
	    ArrayList<Article> listeArt = ConnectionMySql.afficherArticle();
	    for (Article art : listeArt) {%>
	        <tr>
	            <td><%= art.getEAN()%></td>
	            <td><%= art.getVignetteArticle()%></td>
	            <td><%= art.getPrixUnitaireArticle()%></td>
	             <td><%= art.getNutriscoreArticle()%></td>
	            <td><%= art.getLibelleArticle()%></td>
	            <td><%= art.getPoidsArticle()%></td>
	            <td><%= art.getPrixKgArticle()%></td>
	            <td><%= art.getDescriptionCourteArticle()%></td>
	            <td><%= art.getDescriptionLongueArticle()%></td>
	            <td><%= art.getFournisseurArticle()%></td>
	            <td><%= art.getMarque()%></td>
				<td><a href="CtrlPanierServlet?idArticle=<%= art.getEAN()%>">Ajouter au panier</a></td>
	        </tr>
	    <% } %>

    </table>
 <br>
 
 <p>Cliquer pour revenir à la page d'accueil</p>
    
</body>
</html>