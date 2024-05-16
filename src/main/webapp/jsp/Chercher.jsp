<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Article" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="CtrlChercherArticles" method="get">
        <input type="text" name="inputData">
        <input type="submit">
    </form>
    
	<%
	try {
	    ArrayList<Article> articlesTrouve = (ArrayList<Article>)request.getAttribute("articlesTrouve");
	    if (articlesTrouve!=null){
	 %>
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
	             <th>Promotion</th>
	             <th>Id Rayon</th>
	             <th>Id Catégorie</th>
	         </tr>
     <table/>
	     <%
	    	for (Article article : articlesTrouve) {
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
            <td><%= article.getIdCategorie() %></td>
        </tr>
   	<%
	    	}
	    }
	} catch (Exception e) {
	    out.println("<p>Erreur : " + e.getMessage() + "</p>");
	}
	%>
</body>
</html>